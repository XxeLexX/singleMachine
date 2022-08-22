package implement.function;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import org.javatuples.Pair;
import org.javatuples.Quartet;

import java.util.TreeMap;
import java.util.function.Function;

/**
 * This Class refers to the version from Flink-Implement,
 * but to adapt the single machine did also some changes:
 * return value -> Stream
 * vertexId -> Integer
 */
public class RxDCD extends RxBCD implements Function<Pair<Integer, TreeMap<Long, Integer>>, Observable<Quartet<Integer, Long, Long, Integer>>>, io.reactivex.rxjava3.functions.Function<Pair<Integer, TreeMap<Long, Integer>>, ObservableSource<Quartet<Integer, Long, Long, Integer>>> {
    @Override
    public Observable<Quartet<Integer, Long, Long, Integer>> apply(Pair<Integer, TreeMap<Long, Integer>> degreeTrees) {
        return calculateDegreeAndCollect(degreeTrees.getValue0(),degreeTrees.getValue1(),Long.MIN_VALUE,Long.MAX_VALUE);
    }
}
