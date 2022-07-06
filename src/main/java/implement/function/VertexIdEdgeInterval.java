package implement.function;

import graph.TemporalEdge;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import org.javatuples.Triplet;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class VertexIdEdgeInterval implements Function<TemporalEdge, Stream<Triplet<Integer,Long,Long>>> {

    private final DegreeType degreeType;
    private final DimensionType dimensionType;

    /**
     * @param degreeType IN, OUT or BOTH
     * @param dimensionType VALID_TIME or TRANSACTION_TIME
     */
    public VertexIdEdgeInterval(DegreeType degreeType, DimensionType dimensionType){
        this.degreeType = Objects.requireNonNull(degreeType);
        this.dimensionType = Objects.requireNonNull(dimensionType);
    }

    /**
     * Get the Triplet of VertexId and the From-To instead of the Tuple3
     * There is three types of Degree:
     * If IN, the targetId from Edge will be needed,
     * if OUT, the sourceId from Edge will be needed,
     * if BOTH, both Id from Edge will be needed.
     * @param temporalEdge the temporalEdge from the last step @StringToEdge
     * @return Stream of Triplet with VertexId and a From-To time interval
     */
    @Override
    public Stream<Triplet<Integer, Long, Long>> apply(TemporalEdge temporalEdge) {

        // The Stream.Builder will be used hier to store each result
        Stream.Builder<Triplet<Integer, Long, Long>> tripletBuilder = Stream.builder();

        Long from = dimensionType.equals(DimensionType.VALID_TIME) ? temporalEdge.getValidTime().getValue0() : temporalEdge.getTransactionTime().getValue0();
        Long to = dimensionType.equals(DimensionType.VALID_TIME) ? temporalEdge.getValidTime().getValue1() : temporalEdge.getTransactionTime().getValue1();

        switch (degreeType) {
            case IN:
                // collector.collect(new Tuple3<>(temporalEdge.getTargetId(), from, to));
                tripletBuilder.add(new Triplet<>(temporalEdge.getTid(), from, to));
                break;
            case OUT:
                // collector.collect(new Tuple3<>(temporalEdge.getSourceId(), from, to));
                tripletBuilder.add(new Triplet<>(temporalEdge.getSid(), from, to));
                break;
            case BOTH:
                /*
                // collector.collect(new Tuple3<>(temporalEdge.getTargetId(), from, to));
                // collector.collect(new Tuple3<>(temporalEdge.getSourceId(), from, to));
                */
                tripletBuilder.add(new Triplet<>(temporalEdge.getTid(), from, to));
                tripletBuilder.add(new Triplet<>(temporalEdge.getSid(), from, to));
                break;
            default:
                throw new IllegalArgumentException("Invalid vertex degree type [" + degreeType + "].");
        }
        return tripletBuilder.build();
    }
}
