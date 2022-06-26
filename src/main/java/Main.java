import implement.impl.TemporalVertexDegree_SM;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // set a timer to calculate the application's runtime
        long startTime = System.currentTimeMillis();
        long timer;

        DegreeType degreeType = DegreeType.IN;
        DimensionType dimensionType = DimensionType.VALID_TIME;

        /* the wrong results will be caused by Files.walk()
        // x can be 1, 10 or 100
        String x = "1";
        String pathString = "/Users/lxx/Desktop/Data_temporal/citibike_" + x + "_temporal/edges.csv";

        TemporalVertexDegree_SM sm = new TemporalVertexDegree_SM();
        try (Stream<Path> paths = Files.walk(Paths.get(pathString))) {
            System.out.println("\n>>> none-merged result <<<");
            paths.skip(1).forEach(path -> sm.run(path, degreeType, dimensionType));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        */

        // By using merged CSV files can produce the results correctly
        System.out.println("\n>>> merged result <<<\n");
        String fullPath = "/Users/lxx/Desktop/Data_temporal/citibike_edges_1.csv";

        Path path = Paths.get(fullPath);

        TemporalVertexDegree_SM sm2 = new TemporalVertexDegree_SM();
        sm2.run(path, degreeType, dimensionType);

        long endTime = System.currentTimeMillis();
        timer = endTime - startTime;
        System.out.println("Single-Machine Runtime: " + timer + "ms\n");

    }
}
