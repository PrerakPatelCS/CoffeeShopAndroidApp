package com.example.coffeeshop;
/**
 * Provides enum constants for coffee and donut objects.
 * @author Craig Li, Prerak Patel
 */

public class Enums{
    
    /**
     * Provides enum constants for coffee sizes.
     * @author Craig Li, Prerak Patel
     */
    protected enum COFFEE_SIZE {
        SHORT, TALL, GRANDE, VENTI;
    }
    
    /**
     * Provides enum constants for coffee add ins.
     * @author Craig Li, Prerak Patel
     */
    protected enum COFFEE_ADD_INS {
        CREAM, SYRUP, MILK, CARAMEL, WHIPPED_CREAM;
    }
    
    /**
     * Provides enum constants for donut flavors.
     * @author Craig Li, Prerak Patel
     */
    protected enum DONUT_FLAVORS {
        APPLE_CRUMB, BLUEBERRY, BOSTON_CREME,
        CHOCOLATE_GLAZED, CREME_BRULEE, GLAZED,
        MARBLE_FROSTED, RASPBERRY_JELLY, STRAWBERRY_SPRINKLE;
    }
}
