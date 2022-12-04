package com.example.coffeeshop;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity{
    private StoreOrders storeOrders;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private ArrayList<String> formattedOrder = new ArrayList<>();
    private ListView storeOrderDisplay;
    private ArrayAdapter adapter;


    /**
     * Removes an item from your order
     * @param obj the object to be removed from the current order.
     */
    private void remove(Object obj) {

        String itemToRemove = obj.toString();
        String string  = new String();
        for(int i = 0; i < storeOrders.getStoreOrders().size(); i++) {
            string = display(storeOrders.getStoreOrders().get(i));

            if(itemToRemove.equals(string)){
                storeOrders.getStoreOrders().remove(i);
                formattedOrder.remove(obj);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }


    /**
     * @param obj
     * @return String with all data needed for display
     */
    private String display(Object obj){
        String string = new String();
        if(obj instanceof Order){
            Order order = (Order) obj;
            string += "Order Number " + order.getOrderNumber() + "\n";
            string += order.print();
            double subTotal = order.updateOrderTotal();
            double orderTax = order.getOrderTax();
            double orderTotal = subTotal + orderTax;
            string += "Subtotal = " + money.format(subTotal) + " ";
            string += "Tax = " + money.format(orderTax) + " ";
            string += "Total = " + money.format(orderTotal);
        }
        return string;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        storeOrders = MainActivity.getStoreOrders();
        String string = new String();
        storeOrderDisplay = (ListView)findViewById(R.id.storeOrderDisplay);
        for(Order order : storeOrders.getStoreOrders()){
            string = display(order);
            formattedOrder.add(string);
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                formattedOrder);
        storeOrderDisplay.setAdapter(adapter);

        storeOrderDisplay.setOnItemClickListener((parent, view, position, id) -> {
            //set prompt to ask for confirmation of removal, if yes, remove, else exit confirmation

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(R.string.remove_confirm).setTitle(R.string.remove_title);
            alert.setPositiveButton(R.string.yes, (dialog, which) -> {

                remove(storeOrderDisplay.getItemAtPosition(position));
                Toast.makeText(this, R.string.item_removed, Toast.LENGTH_SHORT).show();
            });

            alert.setNegativeButton(R.string.no, (dialog, which) -> {
                //do nothing? close alert?
            });

            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }


}
