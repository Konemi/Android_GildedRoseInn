package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Application;
import android.content.res.Configuration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by omelj on 12/02/16.
 */
public class ItemList extends Application
{
    private ArrayList<Item> itemList;
    private int wallet;
    private int dayCounter;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.itemList= new ArrayList<>();
    }

    public ItemList() {
        super();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setItem(Item item)
    {
        this.itemList.add(item);
    }

    public void setItems(ArrayList<Item> itemList)
    {
        this.itemList = itemList;
    }

    public void setWallet(int sold)
    {
        this.wallet=sold;
    }

    public void setDayCounter(int number)
    {
        this.dayCounter = number;
    }
    public ArrayList<Item> getItems()
    {
        return this.itemList;
    }

    public int getWallet()
    {
        return this.wallet;
    }

    public int getDayCounter() {return this.dayCounter;}
}
