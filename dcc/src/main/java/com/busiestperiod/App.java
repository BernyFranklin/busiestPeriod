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
        // Create random obj
        Random rand = new Random();
        // Set var for count
        int count = rand.nextInt(15);

        return count;
    }
    // Generate random event
    private static EntryData generateData(Long timestamp) {
        int n = setCount();
        String t = chooseType();
        EntryData newEntry = new EntryData(timestamp, n, t);

        return newEntry;
    }
    // Generate shift
    private static LinkedList<EntryData> generateShift() {
        long time = START;
        LinkedList<EntryData> list = new LinkedList<EntryData>();
        int totalPeeps = 0;

        while (time <= END) {
            if (time == END){
                EntryData temp = new EntryData(time, totalPeeps, "EXIT");
                list.add(temp);
                break;
            }
            else{
                EntryData temp = generateData(time);
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
