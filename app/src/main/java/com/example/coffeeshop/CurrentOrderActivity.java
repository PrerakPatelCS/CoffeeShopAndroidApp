package com.example.coffeeshop;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class containing all methods and parameters necessary the Current Order Activity.
 * @author Craig Li, Prerak Patel
 */
public class CurrentOrderActivity extends AppCompatActivity {
    private Order currentOrder;
    private StoreOrders storeOrders;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    private ArrayList<String> formattedOrder = new ArrayList<>();

    private TextView currOrderSubtotalDisplay, currOrderTaxDisplay, currOrderTotalDisplay;
    private Button addOrderToStoreOrders;
    private ListView currOrderDisplay;
    private ArrayAdapter adapter;

    /**
     * Updates the Current Order UI with the current subtotal, tax and total of the
     * current order.
     */
    private void updateCosts() {

        double subTotal = currentOrder.updateOrderTotal();
        double orderTax = currentOrder.getOrderTax();
        double orderTotal = subTotal + orderTax;

        currOrderSubtotalDisplay.setText(money.format(subTotal));
        currOrderTaxDisplay.setText(money.format(orderTax));
        currOrderTotalDisplay.setText(money.format(orderTotal));
    }

    /**
     * Checks to see if the order is empty, disables buttons if order is empty.
     */
    private void checkIfEmpty() {
        if(currentOrder.getOrderList().size() == 0) {
            addOrderToStoreOrders.setEnabled(false);

        }
        else{
            addOrderToStoreOrders.setEnabled(true);
        }
    }

    /**
     * Removes an item from your order
     * @param obj the object to be removed from the current order.
     */
    private void remove(Object obj) {

        Object removeThisObj = obj;
        String itemToRemove = obj.toString();

        for(int i = 0; i < currentOrder.getOrderList().size(); i++) {

            if(itemToRemove.equals(currentOrder.getOrderList().get(i).print())){
                currentOrder.getOrderList().remove(i);
                formattedOrder.remove(removeThisObj);
                adapter.notifyDataSetChanged();
                updateCosts();
                break;
            }
        }
        checkIfEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        currentOrder = MainActivity.getCurrentOrder();
        storeOrders = MainActivity.getStoreOrders();


        currOrderSubtotalDisplay = (TextView)findViewById(R.id.currOrderSubtotalDisplay);
        currOrderTaxDisplay = (TextView)findViewById(R.id.currOrderTaxDisplay);
        currOrderTotalDisplay = (TextView)findViewById(R.id.currOrderTotalDisplay);

        addOrderToStoreOrders = (Button)findViewById(R.id.addOrderToStoreOrders);

        currOrderDisplay = (ListView)findViewById(R.id.currOrderDisplay);


        for(MenuItem item : currentOrder.getOrderList()){
            formattedOrder.add(item.print());
        }

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                formattedOrder);

        currOrderDisplay.setAdapter(adapter);

        currOrderDisplay.setOnItemClickListener((parent, view, position, id) -> {
            //set prompt to ask for confirmation of removal, if yes, remove, else exit confirmation

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage(R.string.remove_confirm).setTitle(R.string.remove_title);
            alert.setPositiveButton(R.string.yes, (dialog, which) -> {

                remove(currOrderDisplay.getItemAtPosition(position));
                Toast.makeText(this, R.string.item_removed, Toast.LENGTH_SHORT).show();
            });

            alert.setNegativeButton(R.string.no, (dialog, which) -> {
                //do nothing? close alert?
            });

            AlertDialog dialog = alert.create();
            dialog.show();
        });

        addOrderToStoreOrders.setOnClickListener(v -> {
            storeOrders.add(currentOrder);

            formattedOrder.clear();
            adapter.notifyDataSetChanged();
            currentOrder = new Order();
            MainActivity.setCurrentOrder(currentOrder);
            updateCosts();
            checkIfEmpty();


            Toast.makeText(this, R.string.order_placed, Toast.LENGTH_SHORT).show();
        });
        updateCosts();
        checkIfEmpty();
    }

}