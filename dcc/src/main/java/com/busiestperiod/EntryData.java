package com.busiestperiod;

public class EntryData {
    private long timestamp;
    private int count;
    private Enum type;

    public EntryData(long timestamp, int count, Enum type) {
        this.timestamp = timestamp;
        this.count = count;
        this.type = type;
    }
}
