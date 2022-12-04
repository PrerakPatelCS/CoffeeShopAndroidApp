package com.example.coffeeshop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DonutTest {
    private Donut donut = new Donut();

    @Before
    public void setUp() throws Exception {
        System.out.println("Making Donut");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down Donut");
        donut = null;
    }

    @Test
    public void testEquals() {
        Donut donut2 = new Donut();
        assertTrue(donut.equals(donut));
        assertTrue(donut.equals(donut2));
        donut.setFlavor(Enums.DONUT_FLAVORS.BOSTON_CREME.toString());
        assertFalse(donut.equals(donut2));
        donut2.setFlavor(Enums.DONUT_FLAVORS.BOSTON_CREME.toString());
        assertTrue(donut.equals(donut2));
    }

    @Test
    public void itemPrice() {
        donut.setFlavor(Enums.DONUT_FLAVORS.BOSTON_CREME.toString());
        donut.setQuantity(1);
        donut.itemPrice();
        assertEquals(donut.getPrice(), 1.33, .01);
        donut.setQuantity(100);
        donut.itemPrice();
        assertEquals(donut.getPrice(), 133.00, .01);
    }

    @Test
    public void print() {
        assertEquals(donut.print(),"[QTY:0] APPLE_CRUMB Donut [ $0.00 ]");
        donut.setQuantity(1);
        donut.setFlavor(Enums.DONUT_FLAVORS.BLUEBERRY.toString());
        donut.setFlavor(Enums.DONUT_FLAVORS.APPLE_CRUMB.toString());
        assertEquals(donut.print(),"[QTY:1] APPLE_CRUMB Donut [ $1.33 ]");
    }
}