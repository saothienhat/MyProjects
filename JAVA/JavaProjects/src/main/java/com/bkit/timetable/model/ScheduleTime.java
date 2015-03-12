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
public class ScheduleTime {
    private int startHour;
    private int startMinute;
    private int endHour;
    private int endMinute;
    
    /**
     * Method to get field startHour as type int
     *
     * @return the startHour
     */
    public int getStartHour() {
        return startHour;
    }
    
    /**
     * Method to set value for field startHour
     *
     * @param startHour the startHour to set
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }
    
    /**
     * Method to get field startMinute as type int
     *
     * @return the startMinute
     */
    public int getStartMinute() {
        return startMinute;
    }
    
    /**
     * Method to set value for field startMinute
     *
     * @param startMinute the startMinute to set
     */
    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }
    
    /**
     * Method to get field endHour as type int
     *
     * @return the endHour
     */
    public int getEndHour() {
        return endHour;
    }
    
    /**
     * Method to set value for field endHour
     *
     * @param endHour the endHour to set
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    
    /**
     * Method to get field endMinute as type int
     *
     * @return the endMinute
     */
    public int getEndMinute() {
        return endMinute;
    }
    
    /**
     * Method to set value for field endMinute
     *
     * @param endMinute the endMinute to set
     */
    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * Construct new instance of ScheduleTime
     *
     * @param startHour
     * @param startMinute
     * @param endHour
     * @param endMinute
     */
    public ScheduleTime(int startHour, int startMinute, int endHour,
            int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    /**
     * Construct new instance of ScheduleTime
     *
     */
    public ScheduleTime() {
    }

    /**
     * Method to getStartTime
     *
     * @return
     */
    public String getStartTime(){
        return String.valueOf(this.startHour) + ":" + String.valueOf(this.startMinute);
    }
    
    
    /**
     * Method to getEndTime
     *
     * @return
     */
    public String getEndTime(){
        return String.valueOf(this.endHour) + ":" + String.valueOf(this.endMinute);
    }
}
