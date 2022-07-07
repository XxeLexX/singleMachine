import implement.impl.ResultOutput;
import implement.impl.TemporalVertexDegree_SM;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        DegreeType degreeType = DegreeType.BOTH;
        DimensionType dimensionType = DimensionType.VALID_TIME;

        // By using merged CSV files can produce the results correctly
        String path_read = "/Users/lxx/Desktop/Data_temporal/citibike_edges_1.csv";
        String path_write = "/Users/lxx/Desktop/citibike_edges_1_TemporalVertexDegree.csv";
        String path_runTime = "/Users/lxx/Desktop/SM_RUNTIME.csv";

        System.out.println("\n>>> merged result <<<\n" + "READING DATA FROM...> " + path_read);

        Path path_r = Paths.get(path_read);
        //Path path_w = Paths.get(path_write);

        TemporalVertexDegree_SM sm = new TemporalVertexDegree_SM();
        ResultOutput resultOutput = new ResultOutput();

        Long runtime = sm.run(path_r, path_write, degreeType, dimensionType);
        resultOutput.writeRuntime(runtime, path_runTime);

    }
}
