package implement.function;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Stream;

public class DegreeCalculatorDefault extends BaseCalculateDegrees implements Function<Pair<String, TreeMap<Long,Integer>>, Stream<Quartet<String, Long, Long, Integer>>> {

    @Override
    public Stream<Quartet<String, Long, Long, Integer>> apply(Pair<String, TreeMap<Long, Integer>> degreeTrees) {
        return calculateDegreeAndCollect(degreeTrees.getValue0(),degreeTrees.getValue1(),Long.MIN_VALUE,Long.MAX_VALUE).stream();

        //return calculateDegreeAndCollect(degreeTrees.getValue0(),degreeTrees.getValue1(),Long.MIN_VALUE,Long.MAX_VALUE).stream();
    }
}
