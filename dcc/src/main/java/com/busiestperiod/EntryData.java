package com.busiestperiod;

public class EntryData {
    private long timestamp;
    private int count;
    private String type;
    private int remaining;

    public EntryData(long timestamp, int count, String type) {
        this.timestamp = timestamp;
        this.count = count;
        this.type = type;
        remaining = 0;
    }

    public String toString() {
        // Initialize string
        String s = "";
        // Format like a json file
        s = "{ ";
        s += "timestamp: " + timestamp;
        s += ",\t";
        s += "type: " + type;
        s += ",\t";
        s += "count: " + count;
        s += ",\t";
        s += "left in buidling: " + remaining;
        s += " }";

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
    public int remaining() {
        return remaining;
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
    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }
}
