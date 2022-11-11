package com.busiestperiod;

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
    private static final long START = 1668178800;
    private static final long END = 1668207600;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
