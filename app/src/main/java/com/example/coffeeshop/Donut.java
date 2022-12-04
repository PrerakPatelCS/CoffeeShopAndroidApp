package com.example.coffeeshop;

import com.example.coffeeshop.Enums.DONUT_FLAVORS;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Class containing all information and methods necessary for a 
 * Donut object.
 * @author  Prerak Patel
 */

public class Donut extends MenuItem implements Customizable, Serializable {

    final static private double DONUT_PRICE = 1.33;
    private String flavor;
    
    /**
     * Constructor for a new donut.
     */
    public Donut() {
    	this.setPrice(DONUT_PRICE);
    	this.setFlavor(DONUT_FLAVORS.APPLE_CRUMB.toString());
    }

    /**
     * Gets the flavor of the donut.
     * @return the flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Sets the flavor of a donut.
     * @param flavor the flavor to set
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }


    /**
     * Checks to see if two donut objects are equal.
     * @param obj	the object to compare.
     * @return true if both objects are the same donut, false if not.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Donut) {
    		Donut donut = (Donut) obj;
    			
    		if(this.flavor.equals(donut.getFlavor())
    			&& (this.getQuantity() == donut.getQuantity()))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Adds a donut object to the current Order
     * @param obj	object to be added.
     * @return true if successfully added, false if not.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Donut) {
            Donut donut = (Donut) obj;
            this.itemPrice();
            return true;
        }
        return false;
    }


    /**
     * Removes a donut object
     * @param obj	The object to be removed.
     * @return true if successfully removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Donut) {
            obj = null;
            this.itemPrice();
            return true;
        }
        return false;
    }
    /**
     * Calculates and sets the price of a donut.
     */
    public void itemPrice(){

        double total = 0;
        total += DONUT_PRICE;
        total = total * (double) this.getQuantity();
        
        this.setPrice(total);
        
    }
    /**
     * Creates a string containing the donut's information.
     * @return qualities	The information about the donut.
     */
    public String print() {

        DecimalFormat money = new DecimalFormat("$#,##0.00");
        String qualities;
        this.itemPrice();
        qualities = super.print() + this.getFlavor().toString()
        			+ " " + this.getClass().getSimpleName() 
        			+ " [ " + money.format(this.getPrice()) + " ]"; 	

        return qualities;
    }
}
