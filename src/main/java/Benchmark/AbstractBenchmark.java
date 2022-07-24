package Benchmark;

import org.apache.commons.cli.*;

public abstract class AbstractBenchmark {
    protected static final Options OPTIONS = new Options();
    protected static final String DEFAULT_FORMAT = "csv";

    protected static CommandLine parseArguments(String[] args, String className)
            throws ParseException {
        if (args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(className, OPTIONS, true);
            return null;
        }
        return new DefaultParser().parse(OPTIONS, args);
    }
}
