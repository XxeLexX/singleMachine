package graph;

public class Vertex {
    // vertex id
    String v_id;

    // vertex label
    String v_label;

    // graph id that vertex belongs to
    String g_id;

    // vertex properties
    String properties;

    public Vertex(String v_id, String g_id, String v_label, String properties) {
        this.v_id = v_id;
        this.g_id = g_id;
        this.v_label = v_label;
        this.properties = properties;
    }

    public String getV_id() {
        return v_id;
    }

    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    public String getV_label() {
        return v_label;
    }

    public void setV_label(String v_label) {
        this.v_label = v_label;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "v_id='" + v_id + '\'' +
                ", v_label='" + v_label + '\'' +
                ", g_id='" + g_id + '\'' +
                ", properties='" + properties + '\'' +
                '}';
    }
}
