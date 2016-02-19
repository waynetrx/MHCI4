package com.mhci4.mhci4.library;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
//        ApplicationInfo item = getItem(position);
//        holder.item_desc.setImageDrawable(item.loadIcon(getPackageManager()));
//        holder.item_qty.setText(item.loadLabel(getPackageManager()));
        return convertView;
    }

    class ViewHolder {
        EditText item_desc;
        EditText item_qty;

        public ViewHolder(View view) {
            item_desc = (EditText) view.findViewById(R.id.et_desc_item);
            item_qty = (EditText) view.findViewById(R.id.et_qty_item);
            view.setTag(this);
        }
    }
}
