package implement.impl;

import implement.function.RxDCD;
import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import labor.playground.BuildTree;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Rx_SM {
    public Long run(String filePath_read, String filePath_write, DegreeType degreeType, DimensionType dimensionType){
        long startTime = System.currentTimeMillis();
        Path file_input = Paths.get(filePath_read);

        if (Files.exists(file_input)) {
            try (Stream<String> tempStream = Files.lines(file_input)) {

                Stream<Triplet<Integer, Long, Long>> temp= tempStream
                        .map(StringToEdge::convertToTemporalEdge)
                        .flatMap(new VertexIdEdgeInterval(degreeType, dimensionType));

                Observable<Quartet<Integer, Long, Long, Integer>> ObservableQuartet = Observable.fromStream(temp)
                        .groupBy(Triplet::getValue0)
                        .flatMapSingle(io.reactivex.rxjava3.core.Observable::toList)
                        .map(BuildTree::buildTrees)
                        .flatMap(new RxDCD());
                        //.subscribe();
                        //.filter(s -> Objects.equals(s.getValue0(), "5f1e9731c4006b674a553279".hashCode() )
                                //&& Objects.equals(s.getValue1(), new Long("1485703998000"))
                        //       )
                        //.forEach(System.out::println);

                writeTemporalVertexDegree(ObservableQuartet, filePath_write);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private void writeTemporalVertexDegree(Observable<Quartet<Integer, Long, Long, Integer>> temporalDegrees, String filePath_write){

        File degreeOutputFile = new File(filePath_write);

        try (FileWriter degreeWriter = new FileWriter(degreeOutputFile)){
            temporalDegrees.forEach(s-> {
                try {
                    degreeWriter.write(s.toString() + '\n');
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
