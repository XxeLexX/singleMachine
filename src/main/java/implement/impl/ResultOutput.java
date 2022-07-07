package implement.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class ResultOutput {
    public void writeRuntime(Long runtime, String outputPath){

        File runTimeOutputFile = new File(outputPath);

        try (FileWriter runtimeWriter = new FileWriter(runTimeOutputFile, true)){
            runtimeWriter.write(Long.toString(runtime) + '\n');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* write the Stream to a file

    public void writeDegreeStream(Stream temporalDegreeTree){
    }
    
    */
}
