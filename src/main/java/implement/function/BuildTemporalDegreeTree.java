package implement.function;

import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.util.*;

public class BuildTemporalDegreeTree{
    /**
     * each TreeMap belongs to one VertexID that stores all the Degrees of one Timestamp
     */
    private static final TreeMap<Long, Integer> degreeTreeMap = new TreeMap<>();

    /**
     * Build the TreeMap for each Vertex
     * @param entries The DIYgroup by results from the last step, Which contains VertexId and all the time interval of it
     * @return Pair<Integer, TreeMap<Long, Integer>> will be given back as the Results, which contains the VertexId and its TreeMap
     */
    public static Pair<Integer, TreeMap<Long, Integer>> makeTree(Map.Entry<Integer, List<Triplet<Integer, Long, Long>>> entries) {
        int vertexId = 0;
        degreeTreeMap.clear();

        for (Triplet<Integer, Long, Long> entry : entries.getValue()){
            if (vertexId == 0){
                vertexId = entry.getValue0();
            }

            degreeTreeMap.merge(entry.getValue1(), 1, Integer::sum);
            degreeTreeMap.merge(entry.getValue2(), -1, Integer::sum);

        }
        return new Pair<>(vertexId, degreeTreeMap);
    }

    /*  put the tree together

    public Quartet<String, TreeMap<Long, Integer>, Long, Long> mergeTuple(
            Quartet<String, TreeMap<Long, Integer>, Long, Long> left,
            Quartet<String, TreeMap<Long, Integer>, Long, Long> right) throws Exception {

        // Put all elements of the right into the left tree and return the left one as merged tree
        for (Map.Entry<Long, Integer> entry : right.getValue1().entrySet()) {
            left.getValue1().merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        return left;
    }
    */
}
