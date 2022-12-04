package com.example.coffeeshop;

import java.io.Serializable;

/**
 * MenuItem class containing all information and methods for a MenuItem object.
 * @author  Prerak Patel
 */
public class MenuItem implements Customizable, Serializable {


	private double price;
    private int quantity;

    /**
     * Sets the item's quantity
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * Gets the item's quantity.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Gets the item's price.
     * @return the price of the item.
     */
    public double getPrice() {
    	return this.price;
    }
    /**
     * Sets the price of an Item.
     * @param price	the value to set the price to.
     */
    public void setPrice(double price) {
    	this.price = price;
    }
    
    /**
     * Adds a menuItem 
     * @param obj	the object to be added.
     * @return true if the object is a menuItem, false if not.
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
        	MenuItem menuItem = (MenuItem) obj;
            return true;
        }
        return false;
    }
    /**
     * Removes a menuItem
     * @param obj	the object to be removed.
     * @return true if item is removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            MenuItem menuItem = null;
            return true;
        }
        return false;
    }
    /**
     * prints a formatted String containing the MenuItem's information.
     * @return qualities	The menuItem's information.
     */
    public String print() {
    	String qualities = "[QTY:" + Integer.toString(this.getQuantity()) 
    						+ "] ";
    	
    	return qualities;
    }
    
    /**
     * Implemented similarly to calculatePayment in Project 2, actual Calculations
     * are in children's implementations.
     */
	public void itemPrice() {
		
	}
}
