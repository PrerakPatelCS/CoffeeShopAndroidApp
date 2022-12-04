package com.example.coffeeshop;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeshop.Enums.DONUT_FLAVORS;

import java.io.Serializable;
import java.util.ArrayList;


public class OrderDonutActivity extends AppCompatActivity implements Serializable {

    private ListView donutFlavors;
    private final static int DONUT_MIN_QTY = 1;
    private final static int DONUT_MAX_QTY = 6;
    private ArrayList<String> donutFlavorsList = new ArrayList<>();
    private Order currentOrder;
    private Donut currentDonut;
    private Button addToOrderBTN;


    /**
     * Checks to see if the order is empty, disables buttons if order is empty.
     */
    private void checkQuantity() {
        if(currentDonut.getQuantity() == 0) {
            addToOrderBTN.setEnabled(false);
        }
        else{
            addToOrderBTN.setEnabled(true);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_donut);

        currentOrder = MainActivity.getCurrentOrder();
        currentDonut = new Donut();

        donutFlavors = (ListView) findViewById(R.id.donutFlavors);

        addToOrderBTN = (Button)findViewById(R.id.addToOrderBTN);


        for( DONUT_FLAVORS flavor : DONUT_FLAVORS.values()){
            donutFlavorsList.add(flavor.toString());
        }

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,
                                donutFlavorsList);

        donutFlavors.setAdapter(adapter);

        donutFlavors.setOnItemClickListener((parent, view, position, id) -> {
            currentDonut.setFlavor(donutFlavorsList.get(position).toString());

            PopupMenu quantity = new PopupMenu(OrderDonutActivity.this, view);

            //inflate Popup menu
            quantity.getMenuInflater().inflate(R.menu.quantity_menu, quantity.getMenu());
            quantity.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    // Toast message
                    Toast.makeText(OrderDonutActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    currentDonut.setQuantity(Integer.parseInt(menuItem.getTitle().toString()));
                    checkQuantity();
                    return true;
                }

            });
            quantity.show();

        });

        addToOrderBTN.setOnClickListener(v -> {
            currentDonut.itemPrice();
            currentOrder.add(currentDonut);
            currentDonut = new Donut();
            checkQuantity();
            Toast.makeText(this, R.string.added_to_order, Toast.LENGTH_SHORT).show();
        });
        checkQuantity();
        currentDonut.itemPrice();

    }

}
