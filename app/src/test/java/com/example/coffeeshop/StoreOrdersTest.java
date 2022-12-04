package com.example.coffeeshop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreOrdersTest {
    private StoreOrders storeOrders = new StoreOrders();
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
        storeOrders = null;
    }

    @Test
    public void findOrder() {
        Order order2 = new Order();
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        order.updateOrderTotal();

        storeOrders.add(order);
        storeOrders.add(order2);
        assertEquals(storeOrders.findOrder(order.getOrderNumber()), 0);
        assertEquals(storeOrders.findOrder(order2.getOrderNumber()), 1);

    }

    @Test
    public void add() {
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        order.updateOrderTotal();

        assertTrue(storeOrders.add(order));
        assertFalse(storeOrders.add(donut));
        assertFalse(storeOrders.add(coffee));
    }

    @Test
    public void remove() {
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        storeOrders.add(order);
        storeOrders.add(order2);
        storeOrders.add(order3);
        storeOrders.add(order4);
        assertTrue(storeOrders.remove(order3));
        assertFalse(storeOrders.remove(order3));

    }

    @Test
    public void print(){
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        donut.setQuantity(2);
        coffee.setQuantity(1);
        coffee.setSize(Enums.COFFEE_SIZE.GRANDE);
        coffee.itemPrice();
        order.add(donut);
        order.add(coffee);
        order.updateOrderTotal();

        storeOrders.add(order);
        storeOrders.add(order2);
        storeOrders.add(order3);
        storeOrders.add(order4);
        storeOrders.remove(order3);
        assertEquals(storeOrders.print(), "--STORE ORDERS--\n" +
                "\n" +
                "Order #: 1\n" +
                "[QTY:2] APPLE_CRUMB Donut [ $2.66 ]\n" +
                "[QTY:1] GRANDE Coffee [ NO ADD INS ]  [ $2.99 ]\n" +
                "\n" +
                "Subtotal: $4.32\n" +
                "Tax: $0.29\n" +
                "Total: $4.61\n" +
                "\n" +
                "Order #: 2\n" +
                "\n" +
                "Subtotal: $0.00\n" +
                "Tax: $0.00\n" +
                "Total: $0.00\n" +
                "\n" +
                "Order #: 4\n" +
                "\n" +
                "Subtotal: $0.00\n" +
                "Tax: $0.00\n" +
                "Total: $0.00\n" +
                "\n" +
                "--END STORE ORDERS.--\n");
    }
}