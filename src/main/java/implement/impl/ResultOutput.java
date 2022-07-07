package implement.impl;

import org.javatuples.Quartet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class ResultOutput {
    /**
     * Write the Runtime to a file
     * @param runtime The Runtime of SingleMachine
     * @param outputPath The path where runtime can be stored
     */
    public void writeRuntime(Long runtime, String outputPath){

        File runTimeOutputFile = new File(outputPath);

        try (FileWriter runtimeWriter = new FileWriter(runTimeOutputFile, true)){
            runtimeWriter.write(Long.toString(runtime) + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Write the results to a file, but still need to optimize
     * @param temporalDegrees The Results of singleMachine
     * @param filePath_write The Path where the results can be stored
     */
    public void writeTemporalVertexDegree(Stream<Quartet<Integer, Long, Long, Integer>> temporalDegrees, String filePath_write){

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
