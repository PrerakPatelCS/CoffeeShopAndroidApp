package com.example.coffeeshop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoffeeTest {
    private Coffee coffee = new Coffee();


    @Before
    public void setUp() throws Exception {
        System.out.println("Making Coffee");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down Coffee");
        coffee = null;
    }

    @Test
    public void findAddIns() {
        assertEquals(coffee.findAddIns(Enums.COFFEE_ADD_INS.CREAM), -1);
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        coffee.add(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
        assertEquals(coffee.findAddIns(Enums.COFFEE_ADD_INS.CREAM), 0);
        assertEquals(coffee.findAddIns(Enums.COFFEE_ADD_INS.CARAMEL), 1);
        assertEquals(coffee.findAddIns(Enums.COFFEE_ADD_INS.WHIPPED_CREAM), 2);
    }

    @Test
    public void printAddins() {
        assertEquals(coffee.printAddins(), "[ ]");
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        assertEquals(coffee.printAddins(), "[ CREAM ]");
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        assertEquals(coffee.printAddins(), "[ CREAM CARAMEL ]");
        coffee.add(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
        assertEquals(coffee.printAddins(), "[ CREAM CARAMEL WHIPPED_CREAM ]");
        coffee.remove(Enums.COFFEE_ADD_INS.CREAM);
        assertEquals(coffee.printAddins(), "[ CARAMEL WHIPPED_CREAM ]");

    }

    @Test
    public void testEquals() {
        Coffee coffee2 = new Coffee();
        assertTrue(coffee.equals(coffee));
        assertTrue(coffee.equals(coffee2));
        coffee.setSize(Enums.COFFEE_SIZE.VENTI);
        coffee2.setSize(Enums.COFFEE_SIZE.GRANDE);
        assertFalse(coffee.equals(coffee2));
        coffee.setQuantity(5);
        coffee2.setSize(Enums.COFFEE_SIZE.VENTI);
        coffee2.setQuantity(3);
        assertFalse(coffee.equals(coffee2));
        coffee2.setQuantity(5);
        assertTrue(coffee.equals(coffee2));
        coffee2.add(Enums.COFFEE_ADD_INS.CREAM);
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        assertFalse(coffee.equals(coffee2));
        coffee2.add(Enums.COFFEE_ADD_INS.CARAMEL);
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        //Addins can be in any order
        assertTrue(coffee.equals(coffee2));
    }


    @Test
    public void add() {
        assertTrue(coffee.add(Enums.COFFEE_ADD_INS.CARAMEL));
        assertFalse(coffee.add(Enums.COFFEE_ADD_INS.CARAMEL));
        assertFalse(coffee.add(coffee));
    }

    @Test
    public void remove() {
        assertFalse(coffee.remove(Enums.COFFEE_ADD_INS.CARAMEL));
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        assertTrue(coffee.remove(Enums.COFFEE_ADD_INS.CARAMEL));
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        coffee.add(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
        assertTrue(coffee.remove(Enums.COFFEE_ADD_INS.CREAM));
    }

    @Test
    public void itemPrice() {
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        coffee.setQuantity(1);
        coffee.itemPrice();
        assertEquals(coffee.getPrice(), 3.19, .01);
        coffee.setQuantity(6);
        coffee.setSize(Enums.COFFEE_SIZE.VENTI);
        coffee.itemPrice();
        assertEquals(coffee.getPrice(), 22.14, .01);

    }

    @Test
    public void print() {
        assertEquals(coffee.print(), "[QTY:0] SHORT Coffee [ NO ADD INS ]  [ $0.00 ]");
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.add(Enums.COFFEE_ADD_INS.CREAM);
        coffee.add(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
        coffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
        coffee.add(Enums.COFFEE_ADD_INS.MILK);
        coffee.add(Enums.COFFEE_ADD_INS.SYRUP);
        coffee.setQuantity(20);
        coffee.itemPrice();
        assertEquals(coffee.print(), "[QTY:20] GRANDE Coffee [ CREAM WHIPPED_CREAM CARAMEL MILK SYRUP ] [ $79.80 ]");
    }
}