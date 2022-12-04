package com.example.coffeeshop;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.example.coffeeshop.Enums.COFFEE_SIZE;
import com.example.coffeeshop.Enums.COFFEE_ADD_INS;

/**
 * Class containing all methods and parameters necessary for a coffee object.
 * @author Craig Li, Prerak Patel
 */

public class Coffee extends MenuItem implements Customizable, Serializable {
    final static private double SHORT = 1.99;
    final static private double TALL = 2.49;
    final static private double GRANDE = 2.99;
    final static private double VENTI = 3.49;
    final static private double [] sizeCost = {SHORT, TALL, GRANDE, VENTI};
    final static private double ADD_INS = 0.20;
    private static final int FAIL_CONDITION = -1;
    private static final int TOTAL_ADD_INS_POSSIBLE = 5;
    private COFFEE_SIZE size;
    
    private int numAddIns = 0;
    public COFFEE_ADD_INS[] addIns = new COFFEE_ADD_INS[TOTAL_ADD_INS_POSSIBLE];
    
    /**
     * Constructor for a new coffee object
     */
    public Coffee() {
    	this.size = COFFEE_SIZE.SHORT;
 
    }

    /**
     * Sets the coffee Size
     * @param size the size to set
     */
    public void setSize(COFFEE_SIZE size) {
        this.size = size;
    }
    /**
     * Gets the coffee size
     * @return size of the coffee
     */
    public COFFEE_SIZE getSize() {
        return this.size;
    }


    /**
     * Finds if a coffee has/does not have a certain add in.
     * @param   coffeeAddins    the add in to search for
     * @return  index	the index of the coffee add in if present,
     * 					-1 or FAIL_CONDITION if not found
     */
    public int findAddIns(COFFEE_ADD_INS coffeeAddins) {
        int index = 0;
        shiftArray();
        for(int i = 0; i < addIns.length; i++) {
            if(addIns[i] == null){
                continue;
            }
            if(addIns[i].equals(coffeeAddins)) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;
    }
    /**
     * Prints a formatted string containing all add ins in a coffee object
     * @return coffeeAddins		A string containing all add Ins in this
     * 							coffee object.
     */
    public String printAddins() {
        String coffeeAddins = "[ ";
        shiftArray();

        for(int i = 0; i < numAddIns; i++) {
            if(addIns[i] == null){
                continue;
            }
            coffeeAddins += addIns[i].name() + " ";
        }
        coffeeAddins += "]";
        return coffeeAddins;
    }
    
    /**
    Shifts an array so that there are no null spaces/gaps.
    */
    private void shiftArray() {
        COFFEE_ADD_INS [] shiftedArray = new COFFEE_ADD_INS[addIns.length];
        int count = 0;
        for(int i = 0; i < addIns.length; i++) {
            if(addIns[i] != null) {
                shiftedArray[count] = addIns[i];
            }
            else{
                count--;
            }
            count++;
        }
        addIns = shiftedArray;
    }


    /**
     * Checks to see if an object is equal to a given coffee.
     * @param obj	the object we are trying to compare
     * @return true if the objects are the same, false if not.
     */
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Coffee) {
    		Coffee coffee = (Coffee) obj;
            boolean sameAddins = true;
            if(this.size.toString().equals(coffee.getSize().toString())){
                for(int i = 0; i < addIns.length; i++) {
                    if(this.addIns[i] == null){
                        continue;
                    }
                    if(coffee.findAddIns(addIns[i]) > -1){
                        sameAddins = true;
                        continue;
                    }
                    sameAddins = false;
                    break;
                }
            }

    		if(this.size.toString().equals(coffee.getSize().toString())
    			&& (sameAddins)
    			&& (this.getQuantity() == coffee.getQuantity()))
    		{
    			return true;
    		}
    	}
    	return false;
    }


    /**
     * Adds addin object to the coffee
     * @param obj	an object to add to the coffee
     * @return true if the object is added, false if not
     */
    @Override
    public boolean add(Object obj) {
    	if(obj instanceof COFFEE_ADD_INS) {
            COFFEE_ADD_INS newAddIn  = (COFFEE_ADD_INS) obj;
            if(findAddIns(newAddIn) == FAIL_CONDITION) {
                numAddIns++;
                addIns[numAddIns - 1] = newAddIn;
                this.itemPrice();
                return true;
            }
        }
        return false;
    }
    /**
     * Removes add in object to the coffee
     * @params	obj	object to be removed from the coffee
     * @return	true if object is removed, false if not.
     */
    @Override
    public boolean remove(Object obj) {
    	if(obj instanceof COFFEE_ADD_INS) {
    		COFFEE_ADD_INS addInToRemove = (COFFEE_ADD_INS) obj;
            final int index = findAddIns(addInToRemove);
            if(index >= 0) {
                addIns[index] = null;
                numAddIns--;
                return true;
            }
    	}

        return false;
    }
    /**
     * Calculates the item price, and sets it by calling setPrice()
     */
    public void itemPrice(){
    	
        COFFEE_SIZE coffeeSize = this.size;
        double total = 0;
        total += sizeCost[coffeeSize.ordinal()];
        total += ADD_INS * numAddIns;
        total = total * this.getQuantity();
        
        this.setPrice(total);
    }
    /**
     * Prints a formatted string containing a representation of a coffee
     * Object.
     * @return qualities	Description of a Coffee Object.
     */
    public String print() {

        DecimalFormat money = new DecimalFormat("$#,##0.00");
        String qualities;
        this.itemPrice();
        qualities = super.print() + this.getSize().toString() + " " 
        			+ this.getClass().getSimpleName().toString() + " ";
        			
        if(numAddIns == 0) {
        	qualities += "[ NO ADD INS ] ";
        }
        else {
			qualities += this.printAddins();
			
        }
        
        qualities += " [ " + money.format(this.getPrice()) + " ]";	

        return qualities;
    }
}
