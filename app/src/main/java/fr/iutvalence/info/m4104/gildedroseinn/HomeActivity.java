package fr.iutvalence.info.m4104.gildedroseinn;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.reflect.Type;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class HomeActivity extends Activity {


    Button shop, inventory, next;
    ItemList ItemList;
    public static ItemList ItemListApp;
    TextView dayDisplay;
    GildedRose gildedRose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        //get the application's reference
        HomeActivity.ItemListApp = (ItemList) getApplication();


        dayDisplay= (TextView) findViewById(R.id.day_text);
        shop = (Button) findViewById(R.id.shop_button);
        inventory = (Button) findViewById(R.id.inventory_button);
        next = (Button) findViewById(R.id.next_button);

        //set the player's wallet to 120$
        HomeActivity.ItemListApp.setWallet(120);

        //set dayCounter to 1
        HomeActivity.ItemListApp.setDayCounter(1);
        dayDisplay.setText("Day " + HomeActivity.ItemListApp.getDayCounter());

        //timer
        final CounterClass timer = new CounterClass(10000, 1000);
        timer.start();

    }

    public void homeActivityClickListener(View view)
    {
        switch (view.getId())
        {
            case R.id.shop_button :
                startShopActivity();
                break;
            case R.id.inventory_button :
                startInventoryActivity();
                break;
            case R.id.next_button :
                nextDay();
                break;
            default :
        }
    }

    private void nextDay()
    {
        //update inventory item data
        for(Item appInventoryItem : HomeActivity.ItemListApp.getItems())
            gildedRose.updateItem(appInventoryItem);
        //update shop item data
        for(Item shopItems : ShopActivity.items)
            gildedRose.updateItem(shopItems);

                HomeActivity.ItemListApp.setDayCounter(HomeActivity.ItemListApp.getDayCounter()+ 1);
        dayDisplay.setText("Day " + HomeActivity.ItemListApp.getDayCounter());
    }

    private void startInventoryActivity()
    {
        this.startActivity(new Intent(this, InventoryActivity.class));
    }

    private void startShopActivity()
    {
        this.startActivity( new Intent(this, ShopActivity.class));
    }

    //re-implementing the countdowntimer class for displaying notifications
    public class CounterClass extends CountDownTimer {

        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }
        @SuppressLint("NewApi")
        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            final Toast toast = Toast.makeText(getApplicationContext(), "Next day in :" + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))) + " sec", Toast.LENGTH_LONG);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 1000);
        }

        @SuppressLint("NewApi")
        @Override
         public void onFinish() {
            // TODO Auto-generated method stub
            nextDay();
            final Toast toast = Toast.makeText(getApplicationContext(), "Next day..", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    toast.cancel();
                }
            }, 500);
            this.start();
        }
    }

}
