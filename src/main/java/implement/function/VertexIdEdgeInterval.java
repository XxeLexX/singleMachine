package implement.function;

import graph.TemporalEdge;
import implement.myEnum.DegreeType;
import implement.myEnum.DimensionType;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public class VertexIdEdgeInterval implements Function<TemporalEdge, Stream<Triplet<String,Long,Long>>> {

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

    @Override
    public Stream<Triplet<String, Long, Long>> apply(TemporalEdge temporalEdge) {

        // maybe something can be used here to optimize the runtime by not using Arraylist
        ArrayList<Triplet<String, Long, Long>> tempSet = new ArrayList<>();

        Long from = dimensionType.equals(DimensionType.VALID_TIME) ? temporalEdge.getValidTime().getValue0() : temporalEdge.getTransactionTime().getValue0();
        Long to = dimensionType.equals(DimensionType.VALID_TIME) ? temporalEdge.getValidTime().getValue1() : temporalEdge.getTransactionTime().getValue1();

        switch (degreeType) {
            case IN:
                // collector.collect(new Tuple3<>(temporalEdge.getTargetId(), from, to));
                tempSet.add(new Triplet<>(temporalEdge.getTid(), from, to));
                break;
            case OUT:
                // collector.collect(new Tuple3<>(temporalEdge.getSourceId(), from, to));
                tempSet.add(new Triplet<>(temporalEdge.getSid(), from, to));
                break;
            case BOTH:
                /*
                // collector.collect(new Tuple3<>(temporalEdge.getTargetId(), from, to));
                // collector.collect(new Tuple3<>(temporalEdge.getSourceId(), from, to));
                */
                tempSet.add(new Triplet<>(temporalEdge.getTid(), from, to));
                tempSet.add(new Triplet<>(temporalEdge.getSid(), from, to));
                break;
            default:
                throw new IllegalArgumentException("Invalid vertex degree type [" + degreeType + "].");
        }

        return tempSet.stream();
    }
}
