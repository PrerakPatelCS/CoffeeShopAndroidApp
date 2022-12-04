package com.example.coffeeshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 * Class containing all methods and parameters necessary for the Order Coffee Activity.
 * @author  Prerak Patel
 */
public class OrderCoffeeActivity extends AppCompatActivity {
    private Order currentOrder;
    private Coffee currentCoffee;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");

    private CheckBox cream_CB, whippedCream_CB, syrup_CB, milk_CB, caramel_CB;
    private Spinner coffeeSizeSPIN, coffeeQTY_SPIN;
    private Button addCoffeeToOrder;
    private TextView coffeeSubTotalDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_coffee);

        //set order
        currentOrder = MainActivity.getCurrentOrder();
        currentCoffee = new Coffee();

        //set UI
        cream_CB = (CheckBox) findViewById(R.id.cream_CB);
        whippedCream_CB = (CheckBox) findViewById(R.id.whippedCream_CB);
        syrup_CB = (CheckBox) findViewById(R.id.syrup_CB);
        milk_CB = (CheckBox) findViewById(R.id.milk_CB);
        caramel_CB = (CheckBox) findViewById(R.id.caramel_CB);

        coffeeSizeSPIN = (Spinner) findViewById(R.id.coffeeSizeSPIN);
        coffeeQTY_SPIN = (Spinner) findViewById(R.id.coffeeQTY_SPIN);

        addCoffeeToOrder = (Button) findViewById(R.id.addCoffeeToOrder);

        coffeeSubTotalDisplay = (TextView) findViewById(R.id.coffeeSubTotalDisplay);

        //set listeners
        cream_CB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentCoffee.add(Enums.COFFEE_ADD_INS.CREAM);
            } else {
                currentCoffee.remove(Enums.COFFEE_ADD_INS.CREAM);
            }
            updatePrice();
        });

        whippedCream_CB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentCoffee.add(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
            } else {
                currentCoffee.remove(Enums.COFFEE_ADD_INS.WHIPPED_CREAM);
            }
            updatePrice();
        });

        syrup_CB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentCoffee.add(Enums.COFFEE_ADD_INS.SYRUP);
            } else {
                currentCoffee.remove(Enums.COFFEE_ADD_INS.SYRUP);
            }
            updatePrice();
        });

        milk_CB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentCoffee.add(Enums.COFFEE_ADD_INS.MILK);
            } else {
                currentCoffee.remove(Enums.COFFEE_ADD_INS.MILK);
            }
            updatePrice();
        });

        caramel_CB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                currentCoffee.add(Enums.COFFEE_ADD_INS.CARAMEL);
            } else {
                currentCoffee.remove(Enums.COFFEE_ADD_INS.CARAMEL);
            }
            updatePrice();
        });

        coffeeQTY_SPIN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Updates the price of the coffee if a new quantity is selected
             * @param parentView
             * @param selectedItemView
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePrice();
            }

            /**
             * Empty method, we don't need to take action if nothing is selected
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
        coffeeSizeSPIN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Updates the coffee price when a new size is selected.
             * @param parentView
             * @param selectedItemView
             * @param position
             * @param id
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePrice();
            }

            /**
             * Empty method, we don't need to take action if nothing is selected
             * @param parentView
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        addCoffeeToOrder.setOnClickListener(v -> {
            updatePrice();
            currentOrder.add(currentCoffee);
            currentCoffee = null;
            currentCoffee = new Coffee();
            Toast.makeText(this, R.string.added_to_order, Toast.LENGTH_SHORT).show();
        });


    }

    /**
     * Reads add ins and sizes selected, then updates the coffeeSubTotalDisplay in
     * the UI with the price.
     */
    private void updatePrice() {
        String coffeeSizeString = coffeeSizeSPIN.getSelectedItem().toString();
        for (Enums.COFFEE_SIZE size : Enums.COFFEE_SIZE.values()) {
            if (size.toString().equals(coffeeSizeString)) {
                currentCoffee.setSize(size);
                break;
            }
        }
        currentCoffee.setQuantity(Integer.parseInt(coffeeQTY_SPIN.getSelectedItem().toString()));
        currentCoffee.itemPrice();

        coffeeSubTotalDisplay.setText(money.format(currentCoffee.getPrice()));
    }
}