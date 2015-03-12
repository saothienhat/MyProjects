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
public enum DayType {
    MONDAY(2, "Monday"),
    TUESDAY(3, "Tuesday"),
    WEDNESDAY(4, "Wednesday"),
    THURSDAY(5, "Thursday"),
    FRIDAY(6, "Friday"),
    SATURDAY(7, "Saturday"),
    SUNDAY(1, "Sunday")    
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
     * Construct new instance of DayType
     *
     * @param index
     * @param name
     */
    DayType(int index, String name) {
        this.index = index;
        this.name = name;
    }
    
    
}
