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
public class Subject {
    private String name;
    private ColorType colorType;
    
    /**
     * Method to get field name as type String
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method to set value for field name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Method to get field colorType as type ColorType
     *
     * @return the colorType
     */
    public ColorType getColorType() {
        return colorType;
    }
    
    /**
     * Method to set value for field colorType
     *
     * @param colorType the colorType to set
     */
    public void setColorType(ColorType colorType) {
        this.colorType = colorType;
    }

    /**
     * Construct new instance of Subject
     *
     * @param name
     * @param colorType
     */
    public Subject(String name, ColorType colorType) {
        this.name = name;
        this.colorType = colorType;
    }

    /**
     * Construct new instance of Subject
     *
     */
    public Subject() {
    }

}
