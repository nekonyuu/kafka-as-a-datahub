package eu.nyuu.courses.model;

public class SensorEvent {
    private Long timestamp;
    private String source;
    private String metric;
    private Long value;

    public Long getValue() {
        return value;
    }

    public String getMetric() {
        return metric;
    }

    public String getSource() {
        return source;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
