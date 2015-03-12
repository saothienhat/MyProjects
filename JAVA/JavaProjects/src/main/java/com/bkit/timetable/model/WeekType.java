/*
 * Copyright (c) 2015 Robert Bosch GmbH reserves all rights even in 
 * the event of industrial property rights. We reserve all rights of 
 * disposal such as copying and passing on to third parties.
 */


package main.java.projects.timetable.model;


/**
 * @author tbi1hc
 *
 */
public enum WeekType {
    WEEK_FIRST(1, "Week 1"),
    WEEK_SECOND(2, "Week 2"),
    WEEK_THIR(3, "Week 3"),
    WEEK_FOUR(4, "Week 4")
    ;
    
    private int index;
    private String name;
    
    /**
     * Method to get field index as type int
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Method to get field name as type String
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Construct new instance of WeekType
     *
     * @param index
     * @param name
     */
    WeekType(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    
}
