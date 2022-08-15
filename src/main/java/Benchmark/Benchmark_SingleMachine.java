package Benchmark;


import implement.impl.TemporalVertexDegree_SM;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;
import org.apache.commons.cli.CommandLine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Benchmark_SingleMachine extends AbstractBenchmark{

    /**
     * Option to declare the degree type (in, out or both).
     */
    private static final String OPTION_DEGREE_TYPE = "d";
    /**
     * Option to declare the time dimension.
     */
    private static final String OPTION_TIME_DIMENSION = "t";
    /**
     * Option to declare path to indexed input graph
     */
    private static final String OPTION_INPUT_PATH = "i";
    /**
     * Option to declare the graph input format (csv or indexed).
     */
    private static final String OPTION_INPUT_FORMAT = "f";
    /**
     * Option to declare path to output graph
     */
    private static final String OPTION_OUTPUT_PATH = "o";
    /**
     * Option to declare output path to csv file with execution results
     */
    private static final String OPTION_CSV_PATH = "c";
    /**
     * The degree type (IN, OUT or BOTH).
     */
    private static String DEGREE_TYPE;
    /**
     * The time dimension to consider (VALID_TIME or TRANSACTION_TIME)
     */
    private static String TIME_DIMENSION;

    /**
     * Used input path
     */
    static String INPUT_PATH;
    /**
     * Used input format (csv or indexed)
     */
    static String INPUT_FORMAT;
    /**
     * Used output path
     */
    static String OUTPUT_PATH;
    /**
     * Used csv path
     */
    private static String CSV_PATH;

    static {
        OPTIONS.addRequiredOption(OPTION_INPUT_PATH, "input", true, "Path to source files.");
        OPTIONS.addOption(OPTION_INPUT_FORMAT, "format", true, "Input graph format (csv (default), indexed).");
        OPTIONS.addRequiredOption(OPTION_OUTPUT_PATH, "output", true, "Path to output file.");
        OPTIONS.addRequiredOption(OPTION_CSV_PATH, "csv", true,
                "Path to csv result file (will be created if not available).");
        //OPTIONS.addOption(OPTION_COUNT_RESULT, "count", false, "Only count the results instead of writing them.");
        OPTIONS.addRequiredOption(OPTION_DEGREE_TYPE, "degreeType", true, "The degree type (IN, OUT or BOTH).");
        OPTIONS.addRequiredOption(OPTION_TIME_DIMENSION, "dimension", true,
                "The time dimension (VALID_TIME or TRANSACTION_TIME)");
        //OPTIONS.addOption(OPTION_VERTEX_TIME, "includeVertexTime", false, "Does the vertex time needs to be included?");
    }
    /**
     * Reads main arguments (input path, output path, csv path and count flag) from command line.
     *
     * @param cmd command line
     */
    static void readBaseCMDArguments(CommandLine cmd) {
        INPUT_PATH   = cmd.getOptionValue(OPTION_INPUT_PATH);
        INPUT_FORMAT = cmd.getOptionValue(OPTION_INPUT_FORMAT, DEFAULT_FORMAT);
        OUTPUT_PATH  = cmd.getOptionValue(OPTION_OUTPUT_PATH);
        CSV_PATH     = cmd.getOptionValue(OPTION_CSV_PATH);
    }

    public static void main(String[] args) throws Exception {

        CommandLine cmd = parseArguments(args, Benchmark_SingleMachine.class.getName());

        if (cmd == null) {
            System.out.println(">>> CMD ERROR <<<");
            return;
        }

        // read cmd arguments
        readBaseCMDArguments(cmd);
        DEGREE_TYPE = cmd.getOptionValue(OPTION_DEGREE_TYPE);
        TIME_DIMENSION = cmd.getOptionValue(OPTION_TIME_DIMENSION);

        // parse arguments
        DegreeType degreeType = DegreeType.valueOf(DEGREE_TYPE);
        DimensionType timeDimension = DimensionType.valueOf(TIME_DIMENSION);

        TemporalVertexDegree_SM TVD_SM = new TemporalVertexDegree_SM();
        Long runtime = TVD_SM.run(INPUT_PATH,OUTPUT_PATH,degreeType,timeDimension);
        writeCSV(runtime);
    }


    /**
     * Writes the specified line (csvTail) to the csv file given by option {@value OPTION_CSV_PATH}.
     * If the file does not exist, the file will be created and the specified header (csvHead) will be appended
     * as first line.
     *
     * @param csvHead the header (i.e., column names) for the csv file
     * @param csvTail the line to append
     * @throws IOException in case of a IO failure
     */
    static void writeToCSVFile(String csvHead, String csvTail) throws IOException {
        Path path = Paths.get(CSV_PATH);
        List<String> linesToWrite;
        if (Files.exists(path)) {
            linesToWrite = Collections.singletonList(csvTail);
        } else {
            linesToWrite = Arrays.asList(csvHead, csvTail);
        }
        Files.write(path, linesToWrite, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    /**
     * Method to create and add lines to a csv-file for result runtime tracking.
     *
     * @throws IOException exception during file writing
     */
    private static void writeCSV(Long runtime_SM) throws IOException {
        String head = String.format("%s|%s|%s|%s", "dataset", "degreeType", "timeDimension", "Runtime(s)");
        String tail = String.format("%s|%s|%s|%s", INPUT_PATH, DEGREE_TYPE,
                TIME_DIMENSION, Math.round(runtime_SM/1000.0));
        writeToCSVFile(head, tail);
    }
}
