package fr.iutvalence.info.m4104.gildedroseinn;

/**
 * Created by omelj on 05/02/16.
 */
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

/*Adapter for both Inventory and shop activities*/
public class CustomAdapter extends BaseAdapter{
    public static Item [] result;
    Context context;
    public static ItemList ItemList;
    private static LayoutInflater inflater=null;
    public static View rowView;
    boolean isInventory;

    protected int itemImage;
    protected int itemQuality;
    protected int itemSellIn;
    protected String itemName;



    public CustomAdapter(Activity MainActivity, Item[] prgmNameList, ItemList ItemList) {
        // TODO Auto-generated constructor stub
        CustomAdapter.result=prgmNameList;
        context=MainActivity;
        if(MainActivity instanceof ShopActivity)
        {
            isInventory = false;
        }
        else
        {
            isInventory = true;
        }
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        CustomAdapter.ItemList = ItemList;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return CustomAdapter.result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv,tv2,tv3,tv4;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        itemImage = CustomAdapter.result[position].getImage();
        itemQuality = CustomAdapter.result[position].getQuality();
        itemSellIn = CustomAdapter.result[position].getSellIn();
        itemName = CustomAdapter.result[position].getName();
        CustomAdapter.rowView = inflater.inflate(R.layout.item_list, null);

        Toast.makeText(context, "Nb item enregistreuent " + CustomAdapter.ItemList.getItems().size(), Toast.LENGTH_LONG);

        if(result == null)
        {
            Toast.makeText(context, "Inventory is empty", Toast.LENGTH_LONG).show();
        }
        else {
            holder=new Holder();
            holder.tv=(TextView) rowView.findViewById(R.id.textView1);
            holder.tv2=(TextView) rowView.findViewById(R.id.textView2);
            holder.tv3=(TextView) rowView.findViewById(R.id.textView3);
            holder.tv4=(TextView) rowView.findViewById(R.id.textView4);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

            holder.tv.setText(itemName);
            holder.tv2.setText("Quality : " + itemQuality);
            holder.tv3.setText("Sell in : " + itemSellIn);
            holder.tv4.setText("" + (itemQuality*2 + 1) + " $");
            holder.img.setImageResource(itemImage);
        }
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                itemImage = CustomAdapter.result[position].getImage();
                itemQuality = CustomAdapter.result[position].getQuality();
                itemSellIn = CustomAdapter.result[position].getSellIn();
                itemName = CustomAdapter.result[position].getName();
                if (!isInventory) {
                    if(CustomAdapter.ItemList.getItems().size() <6 && CustomAdapter.ItemList.getWallet() >= (itemQuality*2 + 1)) {
                        //update the values in the application
                        CustomAdapter.ItemList.setItem(new Item(itemName, itemImage, itemSellIn, itemQuality));
                        CustomAdapter.ItemList.setWallet(CustomAdapter.ItemList.getWallet() - itemSellIn);
                        //we notice the user that he bought an item, the toast pop-up is automatically stopped after 1 sec
                        final Toast toast = Toast.makeText(context, "You bought " + itemName, Toast.LENGTH_LONG);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);
                    }
                    else
                    {
                        final Toast toast = Toast.makeText(context, "You don't have enough money or your inventory is full ", Toast.LENGTH_LONG);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 1000);
                    }
                } else {

                    //deleting the item from the inventory and refresh the listView
                    Item deletedItem = CustomAdapter.ItemList.getItems().get(position);
                    CustomAdapter.ItemList.getItems().remove(deletedItem);
                    CustomAdapter.result=InventoryActivity.ItemList.getItems().toArray(new Item[]{});

                    final Toast toast = Toast.makeText(context, "You used " + itemName, Toast.LENGTH_LONG);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 1000);
                    notifyDataSetChanged();
                }
            }
        });
        return rowView;
    }

}
