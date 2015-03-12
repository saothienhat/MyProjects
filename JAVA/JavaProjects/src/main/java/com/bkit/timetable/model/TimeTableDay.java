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
public class TimeTableDay {
    private DayType dayType;

    
    /**
     * Method to get field dayType as type DayType
     *
     * @return the dayType
     */
    public DayType getDayType() {
        return dayType;
    }

    
    /**
     * Method to set value for field dayType
     *
     * @param dayType the dayType to set
     */
    public void setDayType(DayType dayType) {
        this.dayType = dayType;
    }


    /**
     * Construct new instance of TimeTableDay
     *
     */
    public TimeTableDay() {
    }
    

}
