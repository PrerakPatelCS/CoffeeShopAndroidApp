package com.example.coffeeshop;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Order class containing methods and information about a current order.
 * @author Craig Li, Prerak Patel
 *
 */
public class Order implements Customizable, Serializable {

    private int orderNumber;
    private ArrayList<MenuItem> orderList;
    private static final int FAIL_CONDITION = -1;
    final static double TAX = 0.06625;
    private double orderSubtotal;


    /**
     * Gets the order number
     * @return the orderNum
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }
    /**
     * Sets the order number
     * @param orderNumber	the order number to set.
     */
    public void setOrderNumber(int orderNumber) {
    	this.orderNumber = orderNumber;
    }
    /**
     * Getter for an order
     * @return the orderList
     */
    public ArrayList<MenuItem> getOrderList() {
        return orderList;
    }
    /**
     * Constructor for a new order
     */
    public Order() {
        this.orderList = new ArrayList<MenuItem>();
        this.orderSubtotal = 0;
    }
    /**
     * checks to see if an order is the correct order
     * @return true if the two orders are the same, false if not.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Order) {
    		Order order = (Order) obj;
    		if(this.orderList.equals(order.orderList)) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * Adds an object to an order
     * @param obj	the object to be added to an order
     * @return 	true if object successfully added, false if not.
     */
    @Override
    public boolean add(Object obj) {
        this.removeNulls();
        if(obj instanceof MenuItem) {
            
            MenuItem tempObj = (MenuItem) obj;
            orderList.add(tempObj);
            this.orderSubtotal = this.updateOrderTotal();

            return true;
        }
        return false;
    }
    /**
     * Removes a MenuItem from an order
     * @param obj	the object to be added to an order
     * @return	true if successful, false if not.
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem) {
        	this.removeNulls();
        	MenuItem deleteThisItem = (MenuItem) obj;
            final int index = find(deleteThisItem);
            if(index >= 0) {
            	
            	orderList.remove(index);
            	this.orderSubtotal = this.updateOrderTotal();
                
                return true;
            }
            return false;
        }
        return false;
    }
    /**
     * Finds the index of a menuItem in the Orders arraylist.
     * @param menuItem	the item to search for
     * @return index	the index of the item that was sought
     */
    private int find(MenuItem menuItem) {
        int index = 0;
        for(int i = 0; i < orderList.size(); i++) {
            if(orderList.get(i).equals(menuItem)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    
    /**
    Removes all null values from an arraylist.
    */
    private void removeNulls() {
    	while(orderList.remove(null)){
    		//removes all null entries from arraylist
    	}
    }

    /**
     * Gets the current Order subtotal.
     * @return	this.orderTotalCost	total cost of the order
     */
    public double updateOrderTotal() {
    	double total = 0;
    	this.removeNulls();
        for(MenuItem menuItems : orderList) {
            total += menuItems.getPrice();
            //totalTax += menuItems.itemPrice();
        }
        this.orderSubtotal = total;
        return this.orderSubtotal;
    }

    /**
     * Gets the amount that is paid towards tax on a certain order.
     * @return taxOnOrder	the amount of tax on an order
     */
    public double getOrderTax() {
    	double taxOnOrder = this.orderSubtotal * TAX;
    	
    	return taxOnOrder;
    }
    /**
     * Sets the current Order Total/Cost
     * @param amount	double value to set order total to
     */
    public void setOrderTotal(double amount) {
    	this.orderSubtotal = amount;
    }
    /**
     * Gets the order subtotal.
     * @return the subtotal.
     */
    public double getSubtotal() {
    	return this.orderSubtotal;
    }
    
    /**
     * Prints an order based on the given menu items.
     * @return orders	String containing a representation of all order items.
     */
    public String print() {
        String orders = "";       
        this.removeNulls();
        
        for(MenuItem menuItems : orderList) {
            orders += menuItems.print() + '\n';
        }

        return orders;
    }

}
