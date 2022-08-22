package implement.impl;

import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;
import io.reactivex.rxjava3.core.Observable;
import implement.function.BT;
import org.javatuples.Triplet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * singleMachine by using RxJava
 * TBD
 */
public class Rx_SM_v2 {
    public Long run(String filePath_read,  String filePath_write, DegreeType degreeType, DimensionType dimensionType){
        long startTime = System.currentTimeMillis();
        Path path = Paths.get(filePath_read);

        if (Files.exists(path)) {
            try (Stream<String> tempStream = Files.lines(path)) {

                Stream<Triplet<Integer, Long, Long>> temp= tempStream
                        .map(StringToEdge::convertToTemporalEdge)
                        .flatMap(new VertexIdEdgeInterval(degreeType, dimensionType));

                Observable.fromStream(temp)
                        .toMultimap(Triplet::getValue0)
                        .subscribe(o->BT.buildTrees(o, filePath_write)).dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }
}
