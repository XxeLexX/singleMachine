package implement.impl;

import implement.function.RxDCD;
import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;
import io.reactivex.rxjava3.core.Observable;
import labor.playground.BuildTree;
import org.javatuples.Triplet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rx_SM {
    public void run(Path filePath_read, DegreeType degreeType, DimensionType dimensionType){

        long startTime = System.currentTimeMillis();

        if (Files.exists(filePath_read)) {
            try (Stream<String> tempStream = Files.lines(filePath_read)) {

                Stream<Triplet<Integer, Long, Long>> temp= tempStream
                        .map(StringToEdge::convertToTemporalEdge)
                        .flatMap(new VertexIdEdgeInterval(degreeType, dimensionType));

                Observable.fromStream(temp)
                        .groupBy(Triplet::getValue0)
                        .flatMapSingle(Observable::toList)
                        .map(BuildTree::buildTrees)
                        .flatMap(new RxDCD())
                        .subscribe();
                        //.filter(s -> Objects.equals(s.getValue0(), "5f1e9731c4006b674a553279".hashCode() )
                                //&& Objects.equals(s.getValue1(), new Long("1485703998000"))
                        //       )
                        //.forEach(System.out::println);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

}
