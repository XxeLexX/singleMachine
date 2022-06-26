package graph;

public class Edge {
    // Edge id
    String e_id;

    // graph id that Edge belongs to
    String g_id;

    // source id of Edge
    String sid;

    // target id of Edge
    String tid;

    // Edge label
    String e_label;

    // Edge properties
    String properties;

    public Edge(String e_id, String g_id, String sid, String tid, String e_label,  String properties) {
        this.e_id = e_id;
        this.g_id = g_id;
        this.sid = sid;
        this.tid = tid;
        this.e_label = e_label;
        this.properties = properties;
    }

    public String getE_id() {
        return e_id;
    }

    public void setE_id(String e_id) {
        this.e_id = e_id;
    }

    public String getE_label() {
        return e_label;
    }

    public void setE_label(String e_label) {
        this.e_label = e_label;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "e_id='" + e_id + '\'' +
                ", e_label='" + e_label + '\'' +
                ", g_id='" + g_id + '\'' +
                ", sid='" + sid + '\'' +
                ", tid='" + tid + '\'' +
                ", properties='" + properties + '\'' +
                '}';
    }
}
