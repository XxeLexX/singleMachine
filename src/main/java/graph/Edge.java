package graph;

public class Edge {
    // Edge id
    int e_id;

    // graph id that Edge belongs to
    int g_id;

    // source id of Edge
    int sid;

    // target id of Edge
    int tid;

    // Edge label
    String e_label;

    // Edge properties
    String properties;

    public Edge(int e_id, int g_id, int sid, int tid, String e_label,  String properties) {
        this.e_id = e_id;
        this.g_id = g_id;
        this.sid = sid;
        this.tid = tid;
        this.e_label = e_label;
        this.properties = properties;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public String getE_label() {
        return e_label;
    }

    public void setE_label(String e_label) {
        this.e_label = e_label;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
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
