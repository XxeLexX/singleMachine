package implement.impl;

import implement.function.BuildTemporalDegreeTree;
import implement.function.DegreeCalculatorDefault;
import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemporalVertexDegree_SM {

    public Long run(String filePath_read, String filePath_write, DegreeType degreeType, DimensionType dimensionType){
        // set a timer to calculate the application's runtime
        long startTime = System.currentTimeMillis();

        Path file_input = Paths.get(filePath_read);
        if (Files.exists(file_input)){
            try (Stream<String> tempStream = Files.lines(file_input)){

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
                writeTemporalVertexDegree(temporalDegree, filePath_write);

                /*cheak the results
                temporalDegree.filter(s -> Objects.equals(s.getValue0(), "5f1e9731c4006b674a553279".hashCode() )
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

    /**
     * Write the results to a file, but still need to optimize
     * @param temporalDegrees The Results of singleMachine
     * @param filePath_write The Path where the results can be stored
     */
    private void writeTemporalVertexDegree(Stream<Quartet<Integer, Long, Long, Integer>> temporalDegrees, String filePath_write){

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
