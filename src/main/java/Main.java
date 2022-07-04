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

        DegreeType degreeType = DegreeType.BOTH;
        DimensionType dimensionType = DimensionType.VALID_TIME;

        // By using merged CSV files can produce the results correctly
        String fullPath = "/Users/lxx/Desktop/Data_temporal/citibike_edges_10.csv";
        System.out.println("\n>>> merged result <<<\n" + "Data> " + fullPath);

        Path path = Paths.get(fullPath);

        TemporalVertexDegree_SM sm = new TemporalVertexDegree_SM();
        sm.run(path, degreeType, dimensionType);

        long endTime = System.currentTimeMillis();
        timer = endTime - startTime;
        System.out.println("Single-Machine Runtime in MAIN: " + timer + "ms\n");
    }
}
