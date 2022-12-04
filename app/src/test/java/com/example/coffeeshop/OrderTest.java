package com.example.coffeeshop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private Order order = new Order();
    private Coffee coffee = new Coffee();
    private Donut donut = new Donut();
    @Before
    public void setUp() throws Exception {
        System.out.println("Making Order");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("Tearing down Order");
        order = null;
        coffee = null;
        donut = null;
    }

    @Test
    public void add() {
        Object obj = new Object();
        assertTrue(order.add(coffee));
        assertTrue(order.add(donut));
        assertFalse(order.add(obj));
    }

    @Test
    public void remove() {
        order.add(coffee);
        order.add(donut);
        assertTrue(order.remove(coffee));
        assertTrue(order.remove(donut));
        assertFalse(order.remove(donut));
    }

    @Test
    public void updateOrderTotal() {
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        assertEquals(order.updateOrderTotal(), 4.32, .01);

    }

    @Test
    public void getOrderTax() {
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        order.updateOrderTotal();
        assertEquals(order.getOrderTax(), 0.2862, .0001);
    }

    @Test
    public void print() {
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        order.updateOrderTotal();
        assertEquals(order.print(), "[QTY:2] APPLE_CRUMB Donut [ $2.66 ]\n[QTY:1] GRANDE Coffee [ NO ADD INS ]  [ $2.99 ]\n");

    }
}