import implement.impl.TemporalVertexDegree_SM;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        DegreeType degreeType = DegreeType.IN;
        DimensionType dimensionType = DimensionType.VALID_TIME;

        // By using merged CSV files can produce the results correctly
        String path_read = "/Users/lxx/Desktop/Data_temporal/citibike_edges_1.csv";
        String path_write = "/Users/lxx/Desktop/Results/citibike_edges_1_TemporalVertexDegree.csv";
        String path_runTime = "/Users/lxx/Desktop/Results/SM_RUNTIME.csv";

        System.out.println("\n>>> merged result <<<\n" + "READING DATA FROM...> " + path_read);

        //Path path_r = Paths.get(path_read);
        //Path path_w = Paths.get(path_write);

        TemporalVertexDegree_SM sm = new TemporalVertexDegree_SM();

        Long runtime = sm.run(path_read, path_write, degreeType, dimensionType);

        try (FileWriter runtimeWriter = new FileWriter(path_runTime, true)){
            runtimeWriter.write(Long.toString(runtime) + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
