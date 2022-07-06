package implement.function;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This Class refers to the version from Flink-Implement,
 * but to adapt the single machine did also some changes:
 * return value -> Stream
 * vertexId -> Integer
 */
public class DegreeCalculatorDefault extends BaseCalculateDegrees implements Function<Pair<Integer, TreeMap<Long,Integer>>, Stream<Quartet<Integer, Long, Long, Integer>>> {

    @Override
    public Stream<Quartet<Integer, Long, Long, Integer>> apply(Pair<Integer, TreeMap<Long, Integer>> degreeTrees) {
        return calculateDegreeAndCollect(degreeTrees.getValue0(),degreeTrees.getValue1(),Long.MIN_VALUE,Long.MAX_VALUE);
    }
}
