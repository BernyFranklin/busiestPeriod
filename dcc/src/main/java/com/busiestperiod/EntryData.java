package com.busiestperiod;

public class EntryData {
    private long timestamp;
    private int count;
    private String type;

    public EntryData(long timestamp, int count, String type) {
        this.timestamp = timestamp;
        this.count = count;
        this.type = type;
    }

    public String toString() {
        // Initialize string
        String s = "";
        // Format like a json file
        s = "{ ";
        s += "timestamp: " + timestamp;
        s += ", ";
        s += "count: " + count;
        s += ", ";
        s += "type: " + type;
        s += "}";

        return s;
    }

    // Getters
    public long timestamp() {
        return this.timestamp;
    }
    public int count() {
        return this.count;
    }
    public String type() {
        return this.type;
    }

    // Setters
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setType(String type) {
        this.type = type;
    }
}
