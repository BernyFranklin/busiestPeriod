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
 * timestamps. YOu can assume the building starts empty and ends empty
 *
 */
public class App 
{
    // Constants
    private static final long START = 1668178800;
    private static final long END = 1668207600;
    private static final long FIVE_MIN = 300;
    private static final String ENTRY = "ENTRY";
    private static final String EXIT = "EXIT";

    // Main
    public static void main( String[] args )
    {
        // Create list to store data
        LinkedList<EntryData> list = new LinkedList<EntryData>();
        // Generate random shift of events
        list = generateShift();
        // Print the list
        printShift(list);
        // Create array for busiest time
        Long[] busyBusy = findBusiestTime(list);
        // Print data
        System.out.printf("Busiest time {Start, End}: {%d, %d}", busyBusy[0], busyBusy[1]);
        
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
            type = ENTRY;
        }
        // Else set exit
        else {
            type = EXIT;
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
    private static EntryData generateRandom(Long timestamp, int count) {
        int n = setCount();
        String t = chooseType();
        // Used to make sure count doesn't go negative
        if (n > count && t == EXIT) {
            n = count;
        }
        EntryData newRandom = new EntryData(timestamp, n, t);

        return newRandom;
    }
    // Generate an entry
    private static EntryData generateEntry(Long timestamp) {
        int n = setCount();
        String t = ENTRY;
        EntryData newEntry = new EntryData(timestamp, n, t);

        return newEntry;
    }
    // Generate an exit
    private static EntryData generateExit(Long timestamp, int count) {
        String t = EXIT;
        EntryData newExit = new EntryData(timestamp, count, t);

        return newExit;
    }
    // Generate shift
    private static LinkedList<EntryData> generateShift() {
        // Set start time
        long time = START;
        // Create list to store data
        LinkedList<EntryData> list = new LinkedList<EntryData>();
        // Initialize counter and maxCount
        int totalPeeps = 0;
        // First event will be an entry
        EntryData firstEvent = generateEntry(time);
        list.add(firstEvent);
        // Increment time
        time += FIVE_MIN;
        // Increment count
        totalPeeps += firstEvent.count();

        // Continue to loop to populate a shift of events
        while (time <= END) {
            // If the day is done, evacute all people
            if (time == END){
                EntryData temp = generateExit(time, totalPeeps);
                list.add(temp);
                totalPeeps -= temp.count();
                break;
            }
            // If building empty and during business hours, add people
            else if (totalPeeps == 0 && time < END) {
                EntryData temp = generateEntry(time);
                list.add(temp);
                totalPeeps += temp.count();
            }
            // Random events
            else{
                EntryData temp = generateRandom(time, totalPeeps);
                list.add(temp);
                // Add for entry subtract for exit
                if (temp.type() == ENTRY) {
                    totalPeeps += temp.count();
                }
                else{
                    totalPeeps -= temp.count();
                }
            }
            // Add five minutes to count
            time += FIVE_MIN;
        }
        return list;
    }
    // Find busiest time of day
    private static Long[] findBusiestTime(LinkedList<EntryData> list) {
        // Initialize array to store values
        Long[] busiestTime = new Long[2];
        // Initialize some counters
        int maxPeeps = 0;
        int totalPeeps = 0;
        // Iterate through list and keep track of how many people at any time
        for (EntryData item: list) {
            if (item.type() == ENTRY) {
                totalPeeps += item.count();
            }
            else {
                totalPeeps -= item.count();
            }

            if (totalPeeps > maxPeeps){
                maxPeeps = totalPeeps;
                busiestTime[0] = item.timestamp();
                busiestTime[1] = busiestTime[0] + FIVE_MIN;
            }
        }
        // Return Long[]
        return busiestTime;
    }
    // Print list of data
    private static void printShift(LinkedList<EntryData> list) {
        int buildingCount = 0;

        for (EntryData item: list) {
            if (item.type() == ENTRY) {
                buildingCount += item.count();
            }
            else {
                buildingCount -= item.count();
            }
            System.out.println(item.toString());
            System.out.printf("Total of people in building: %d\n", buildingCount);
        }
    }
}   
