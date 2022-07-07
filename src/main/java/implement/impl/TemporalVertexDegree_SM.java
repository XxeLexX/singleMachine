package implement.impl;

import implement.function.BuildTemporalDegreeTree;
import implement.function.DegreeCalculatorDefault;
import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemporalVertexDegree_SM {

    public Long run(Path filePath_read, String filePath_write, DegreeType degreeType, DimensionType dimensionType){
        // set a timer to calculate the application's runtime
        long startTime = System.currentTimeMillis();

        if (Files.exists(filePath_read)){
            try (Stream<String> tempStream = Files.lines(filePath_read)){

                // For each vertex id, build a degree tree data structure
                Stream<Quartet<Integer, Long, Long, Integer>> temporalDegree = tempStream
                        // 0) Get all Edges
                        .map(StringToEdge::convertToTemporalEdge)
                        // 1) Extract vertex id(s) and corresponding time intervals
                        .flatMap(new VertexIdEdgeInterval(degreeType, dimensionType))
                        // 2.1) Group them by the vertex id
                        .collect(Collectors.groupingBy(Triplet::getValue0))
                        // 2.2) Convert the results to Stream
                        .entrySet()
                        .stream()
                        // 3) Build DegreeTrees for each Vertex
                        .map(BuildTemporalDegreeTree::makeTree)
                        // 4) For each vertex, calculate the degree evolution and ouput a tuple4
                        .flatMap(new DegreeCalculatorDefault());

                // Output the Stream
                ResultOutput resultOutput = new ResultOutput();
                resultOutput.writeTemporalVertexDegree(temporalDegree, filePath_write);
                //temporalDegree.count();

                /* cheak the results
                temporalDegree.filter(s -> Objects.equals(s.getValue0(), "5f1e9732e3aec8851806f875".hashCode() )
                                        && Objects.equals(s.getValue1(), new Long("1485703998000")))
                              .forEach(System.out::println);
                */

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // runtime as return value
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
