package fr.iutvalence.info.m4104.gildedroseinn;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class ShopActivity extends Activity{

    ListView objectList;
    public static ItemList ItemList;
    public static Item[] items = new Item[] {
             new Item("+5 Dexterity Vest",R.drawable.dexterity_vest, 10, 20), //
             new Item("Aged Brie",R.drawable.aged_brie, 2, 0), //
             new Item("Elixir of the Mongoose", R.drawable.elixir_mongoose,5, 7), //
             new Item("Sulfuras, Hand of Ragnaros",R.drawable.sulfuras, 0, 80), //
             new Item("Sulfuras, Hand of Ragnaros",R.drawable.sulfuras, -1, 80),
             new Item("Backstage passes to a TAFKAL80ETC concert",R.drawable.etc_passes, 15, 20),
             new Item("Backstage passes to a TAFKAL80ETC concert",R.drawable.etc_passes, 10, 49),
             new Item("Backstage passes to a TAFKAL80ETC concert",R.drawable.etc_passes, 5, 49),
             new Item("Conjured Mana Cake",R.drawable.conjured_mana_cake, 3, 6) };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);

        ShopActivity.ItemList = (ItemList) getApplication();

        objectList = (ListView) findViewById(R.id.shop_listView);
        objectList.setAdapter(new CustomAdapter(this, ShopActivity.items, ShopActivity.ItemList));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop, menu);
        return true;
    }
}
