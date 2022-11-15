package com.busiestperiod;

import java.util.LinkedList;
import java.util.Random;

/**
 * You are given a list of data entries that represent entries and exits of
 * groups of people into a building. An entry looks like:
 * {"timestamp" : 1526579928, count: 3, "type": "enter"}
 * meaning 3 people entered the building. An exit looks like:
 * {"timestamp" : 1526580382, count: 2, "type": "exit"}
 * This means 2 people left.
 * 
 * Find the busiest period in the building . Return it as a pair (Start, End)
 * timestamps. YOu can assume the building 
 *
 */
public class App 
{
    // Constants
    private static final long START = 1668178800;
    private static final long END = 1668207600;
    private static final long FIVE_MIN = 300;

    // Main
    public static void main( String[] args )
    {
        LinkedList<EntryData> list = new LinkedList<EntryData>();
        list = generateShift();
        for (EntryData item: list) {
            System.out.println(item.toString());
        }
        
    }

    // Generate random entry type
    private static String chooseType() {
        // Create random object
        Random rand = new Random();
        // Random number for choice
        int num = rand.nextInt(2);
        String type = "";
        // If even set ENTRY
        if (num == 0) {
            type = "ENTRY";
        }
        // Else set exit
        else {
            type = "EXIT";
        }

        return type;
    }
    // Generate random count
    private static int setCount() {
        // COunt
        int count = 0;
        // Create random obj
        Random rand = new Random();
        // Set var for count
        while(count == 0) {
            count = rand.nextInt(15);
        }
        return count;
    }
    // Generate random event
    private static EntryData generateData(Long timestamp, int count) {
        int n = setCount();
        String t = chooseType();
        // Used to make sure count doesn't go negative
        if (n > count && t == "EXIT") {
            n = count;
        }
        EntryData newRandom = new EntryData(timestamp, n, t);

        return newRandom;
    }
    // Generate an entry
    private static EntryData generateEntry(Long timestamp) {
        int n = setCount();
        String t = "ENTRY";
        EntryData newEntry = new EntryData(timestamp, n, t);

        return newEntry;
    }
    // Generate an exit
    private static EntryData generateExit(Long timestamp, int count) {
        String t = "EXIT";
        EntryData newExit = new EntryData(timestamp, count, t);

        return newExit;
    }
    // Generate shift
    private static LinkedList<EntryData> generateShift() {
        // Set start time
        long time = START;
        // Create list to store data
        LinkedList<EntryData> list = new LinkedList<EntryData>();
        // Initialize counter
        int totalPeeps = 0;
        // First event will be an entry
        EntryData firstEvent = generateEntry(time);
        // Increment time
        time += FIVE_MIN;
        // Increment count
        totalPeeps += firstEvent.count();

        // Continue to loop to populate a shift of events
        while (time <= END) {
            if (time == END){
                EntryData temp = generateExit(time, totalPeeps);
                list.add(temp);
                break;
            }
            else if (totalPeeps == 0) {
                EntryData temp = generateEntry(time);
                list.add(temp);
            }
            else{
                EntryData temp = generateData(time, totalPeeps);
                list.add(temp);
                // Add for entry subtract for exit
                if (temp.type() == "ENTRY") {
                    totalPeeps += temp.count();
                }
                else{
                    totalPeeps -= temp.count();
                }
            }
            time += FIVE_MIN;
        }
        System.out.printf("Total people left: %d\n", totalPeeps);
        return list;
    }
}   
