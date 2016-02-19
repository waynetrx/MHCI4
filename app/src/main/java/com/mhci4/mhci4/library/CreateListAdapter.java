package com.mhci4.mhci4.library;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mhci4.mhci4.R;

import java.util.ArrayList;

public class CreateListAdapter extends BaseSwipListAdapter{

    private ArrayList<GroceryItem> mItems;
    private Context mContext;

    public CreateListAdapter(Context context,ArrayList<GroceryItem> items)
    {
        mItems = items;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext,
                    R.layout.item_list_create, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        GroceryItem item = (GroceryItem)getItem(position);
        holder.item_desc.setText(item.getDesc());
        holder.item_qty.setText(Integer.toString(item.getQuantity()));
        Log.i("CreateListAdapter","Desc: " + item.getDesc() + ", Qty: " + item.getQuantity());
        return convertView;
    }

    class ViewHolder {
        TextView item_desc;
        TextView item_qty;

        public ViewHolder(View view) {
            item_desc = (TextView) view.findViewById(R.id.et_desc_item);
            item_qty = (TextView) view.findViewById(R.id.et_qty_item);
            view.setTag(this);
        }
    }
}
