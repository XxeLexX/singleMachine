package graph;

import org.javatuples.Pair;

public class TemporalEdge extends Edge{
    private Pair<Long, Long> transactionTime;
    private Pair<Long, Long> validTime;

    public TemporalEdge(String e_id, String g_id, String sid, String tid, String e_label, String properties, Pair<Long, Long> transactionTime, Pair<Long, Long> validTime) {
        super(e_id, g_id, sid, tid, e_label, properties);
        this.transactionTime = transactionTime;
        this.validTime = validTime;
    }

    public Pair<Long, Long> getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Pair<Long, Long> transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Pair<Long, Long> getValidTime() {
        return validTime;
    }

    public void setValidTime(Pair<Long, Long> validTime) {
        this.validTime = validTime;
    }

    @Override
    public String toString() {
        return "TemporalEdge{" +
                "transactionTime=" + transactionTime +
                ", validTime=" + validTime +
                ", e_id='" + e_id + '\'' +
                ", e_label='" + e_label + '\'' +
                ", g_id='" + g_id + '\'' +
                ", sid='" + sid + '\'' +
                ", tid='" + tid + '\'' +
                ", properties='" + properties + '\'' +
                '}';
    }
}
