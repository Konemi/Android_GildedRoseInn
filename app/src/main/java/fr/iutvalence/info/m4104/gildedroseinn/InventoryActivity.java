package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class InventoryActivity extends Activity {

    public static ItemList ItemList;
    ListView objectList;
    CustomAdapter inventoryAdapter;
    TextView soldText;


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("wallet_value", ItemList.getWallet());
        outState.putInt("dayCounter", ItemList.getDayCounter());

        outState.putParcelableArrayList("itemList", ItemList.getItems());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState )
    {
        //initialize the itemlist whether it's been previously saved
        if(savedInstanceState != null && savedInstanceState.containsKey("itemList")){

            ArrayList<Item> itemList= savedInstanceState.getParcelableArrayList("itemList");
            HomeActivity.ItemListApp.setItems(itemList);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_layout);

        InventoryActivity.ItemList = (ItemList) getApplication();

        objectList = (ListView) findViewById(R.id.inventory_listView);
        inventoryAdapter = new CustomAdapter(this,
                InventoryActivity.ItemList.getItems().toArray(new Item[]{}),
                InventoryActivity.ItemList);
        objectList.setAdapter(inventoryAdapter);

        soldText = (TextView) findViewById(R.id.sold_inventory);
        soldText.setText("Sold : " + InventoryActivity.ItemList.getWallet());

    }
}
