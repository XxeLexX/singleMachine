package implement.impl;

import implement.function.BuildTemporalDegreeTree;
import implement.function.DegreeCalculatorDefault;
import implement.function.StringToEdge;
import implement.function.VertexIdEdgeInterval;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Triplet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TemporalVertexDegree_SM {

    public void run(Path filePath_read, DegreeType degreeType, DimensionType dimensionType){

        if (Files.exists(filePath_read)){
            try (Stream<String> tempStream = Files.lines(filePath_read)){
                // For each vertex id, build a degree tree data structure
                Map<String, List<Triplet<String, Long, Long>>> vertexMap = tempStream
                        // 0) Get all Edges
                        .map(StringToEdge::convertToTemporalEdge)
                        // 1) Extract vertex id(s) and corresponding time intervals
                        .flatMap(new VertexIdEdgeInterval(degreeType, dimensionType))
                        // 2) Group them by the vertex id
                        .collect(Collectors.groupingBy(Triplet::getValue0));

                List<Pair<String, TreeMap<Long, Integer>>> degreeTrees = new ArrayList<>();
                for (Map.Entry<String, List<Triplet<String, Long, Long>>> entry : vertexMap.entrySet()){
                    BuildTemporalDegreeTree buildTemporalDegreeTree = new BuildTemporalDegreeTree();
                    Pair<String, TreeMap<Long, Integer>> tempPair = buildTemporalDegreeTree.makeTree(entry);
                    degreeTrees.add(tempPair);
                }
                // 4) For each vertex, calculate the degree evolution and output a tuple
                Stream<Quartet<String, Long, Long, Integer>> temporalDegree = degreeTrees.stream().flatMap(new DegreeCalculatorDefault());
                System.out.println(temporalDegree.count());

                /*test
                temporalDegree
                        .filter(s -> Objects.equals(s.getValue0(), "5f1e9732caa3a7422e2a76da")
                                  //&& Objects.equals(s.getValue1(), new Long("1581665193598"))
                                )
                        .forEach(System.out::println);
                */

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
