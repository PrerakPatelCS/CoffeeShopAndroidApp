package com.example.coffeeshop;

import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    Button store;
    Button orders;
    Button coffee;
    Button donut;

    protected static StoreOrders storeOrder = new StoreOrders();
    protected static Order yourOrder = new Order();


    Button buttonPressed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        store = (Button) findViewById(R.id.storeOrdersButton);
        orders = (Button) findViewById(R.id.currOrdersButton);
        coffee = (Button) findViewById(R.id.orderCoffeeButton);
        donut = (Button) findViewById(R.id.orderDonutButton);

    }

    /**
     Opens a new activity based on the button pressed.
     @param view	 button pressed, used to determine which window is opened.
     */
    public void makeNewStage(View view) {

        Intent intent = new Intent();
        if(donut.getId() == view.getId()){
            intent = new Intent(this, OrderDonutActivity.class);
        }
        else if(coffee.getId() == view.getId()){
            intent = new Intent(this, OrderCoffeeActivity.class);
        }
        else if(orders.getId() == view.getId()){
            intent = new Intent(this, CurrentOrderActivity.class);
        }
        else if(store.getId() == view.getId()){
            intent = new Intent(this, StoreOrdersActivity.class);
        }

        startActivity(intent);
    }
    /**
     * Gets the current Order established in the main menu
     * @return yourOrder	the current order
     */
    public static Order getCurrentOrder() {
        return yourOrder;
    }
    /**
     * Sets the current order in the main menu. (used when adding to store orders)
     * @param order	the order to set to be the current order.
     */
    public static void setCurrentOrder(Order order) {
        yourOrder = order;
    }
    /**
     * Gets the Store Orders
     * @return storeOrder	the current Store Orders.
     */
    public static StoreOrders getStoreOrders() {
        return storeOrder;
    }
}
