/*
 * Copyright (c) 2015 Robert Bosch GmbH reserves all rights even in 
 * the event of industrial property rights. We reserve all rights of 
 * disposal such as copying and passing on to third parties.
 */





/**
 * @author tbi1hc
 *
 */
public enum ColorType {
    EMERALD("Emerald", "2ecc71")
    ;
    
    private String name;
    private String hexCode;
    
    /**
     * Method to get field name as type String
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Method to get field hexCode as type String
     *
     * @return the hexCode
     */
    public String getHexCode() {
        return hexCode;
    }

    /**
     * Construct new instance of ColorType
     *
     * @param name
     * @param hexCode
     */
    ColorType(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }
    
    
    /**
     * Method to getHexHashtagCode
     *
     * @param colorType
     * @return
     */
    public String getHexHashtagCode(ColorType colorType){
        return "#" + colorType.getHexCode();
    }
    
    public int[] getRgbCode(String colorName){
        int result[] = null;
        if(ColorType.EMERALD.getName().equalsIgnoreCase(colorName)) {
            int value[] = {46, 204, 113};
            result =  value;
        }
        else {
            result = new int[3];
        }
        return result;
    }
}
