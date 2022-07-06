package implement.function;

import org.javatuples.Quartet;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * This Class refers to the version from Flink-Implement,
 * but to adapt the single machine did also some changes:
 * return value -> Stream
 * vertexId -> Integer
 */
public abstract class BaseCalculateDegrees {

    private static final String TEMPORAL_VIOLATION_MSG = "Last timestamp [%d] is not smaller than the " +
            "current [%d] for vertex with id [%s]. A chronological order of timestamps is mandatory. Please " +
            "check the temporal integrity of your graph in the given time domain. The operator " +
            "TemporalGraph#updateEdgeValidity() can be used to update an edges validity to ensure its integrity.";

    protected Stream<Quartet<Integer, Long, Long, Integer>> calculateDegreeAndCollect(int vertexId, TreeMap<Long, Integer> degreeTree, Long vertexFromTime, Long vertexToTime) {

        // we store for each timestamp the current degree
        int degree = 0;

        // first degree 0 is from t_from(v) to the first occurrence of a start timestamp
        Long lastTimestamp = vertexFromTime;

        // Use Stream.Builder to store the results
        Stream.Builder<Quartet<Integer, Long, Long, Integer>> tuple4Builder = Stream.builder();

        for (Map.Entry<Long, Integer> entry : degreeTree.entrySet()) {
            // check integrity
            if (lastTimestamp > entry.getKey()) {
                // This should not happen, seems that a temporal constraint is violated
                throw new IllegalArgumentException(String.format(TEMPORAL_VIOLATION_MSG, lastTimestamp, entry.getKey(), vertexId));
            }

            if (lastTimestamp.equals(entry.getKey())) {
                // First timestamp in tree is equal to the lower interval bound of the vertex
                degree += entry.getValue();
                continue;
            }

            // The payload is 0, means the degree does not change and the intervals can be merged
            if (entry.getValue() != 0) {
                //collector.collect(new Tuple4<>(vertexId, lastTimestamp, entry.getKey(), degree));
                tuple4Builder.add(new Quartet<>(vertexId, lastTimestamp, entry.getKey(), degree));
                degree += entry.getValue();
                // remember the last timestamp since it is the first one of the next interval
                lastTimestamp = entry.getKey();
            }
        }

        // last degree is 0 from last occurence of timestamp to t_to(v)
        if (lastTimestamp < vertexToTime) {
            //collector.collect(new Tuple4<>(vertexId, lastTimestamp, vertexToTime, degree));
            tuple4Builder.add(new Quartet<>(vertexId, lastTimestamp, vertexToTime, degree));

        } else if (lastTimestamp > vertexToTime) {
            // This should not happen, seems that a temporal constraint is violated
            throw new IllegalArgumentException(String.format(TEMPORAL_VIOLATION_MSG, lastTimestamp, vertexToTime, vertexId));
        } // else, the ending bound of the vertex interval equals the last timestamp of the edges
        return tuple4Builder.build();
    }
}
