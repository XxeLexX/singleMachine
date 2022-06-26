package implement.function;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.*;

public class BuildTemporalDegreeTree{
    private final TreeMap<Long, Integer> degreeTreeMap;

    public BuildTemporalDegreeTree() {
        this.degreeTreeMap = new TreeMap<>();
    }

    public Pair<String, TreeMap<Long, Integer>> makeTree(Map.Entry<String, List<Triplet<String, Long, Long>>> entries) {
        String vertexId = null;
        degreeTreeMap.clear();

        for (Triplet<String, Long, Long> entry : entries.getValue()){
            if (vertexId == null){
                vertexId = entry.getValue0();
            }

            degreeTreeMap.merge(entry.getValue1(), 1, Integer::sum);
            degreeTreeMap.merge(entry.getValue2(), -1, Integer::sum);

        }
        return new Pair<>(vertexId, degreeTreeMap);
    }

    /* put the tree together
    public Quartet<String, TreeMap<Long, Integer>, Long, Long> reduce(
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
