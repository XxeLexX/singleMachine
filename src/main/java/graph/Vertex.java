package graph;

public class Vertex {
    // vertex id
    int v_id;

    // vertex label
    String v_label;

    // graph id that vertex belongs to
    int g_id;

    // vertex properties
    String properties;

    public Vertex(int v_id, int g_id, String v_label, String properties) {
        this.v_id = v_id;
        this.g_id = g_id;
        this.v_label = v_label;
        this.properties = properties;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getV_label() {
        return v_label;
    }

    public void setV_label(String v_label) {
        this.v_label = v_label;
    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
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
