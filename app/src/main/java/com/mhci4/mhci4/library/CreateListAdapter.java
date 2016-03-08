package com.mhci4.mhci4.library;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhci4.mhci4.R;
import com.mhci4.mhci4.retrofit.Item;
import java.util.List;

import co.dift.ui.SwipeToAction;

public class CreateListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> mItems;
    private Context mContext;



    public class CreateListViewHolder extends SwipeToAction.ViewHolder<Item>
    {

        public TextView tvDesc;
        public TextView tvQuantity;
        public CreateListViewHolder(View v) {
            super(v);
            tvDesc = (TextView)v.findViewById(R.id.et_desc_item);
            tvQuantity = (TextView)v.findViewById(R.id.et_qty_item);
        }

    }

    public CreateListAdapter(Context context,List<Item> items)
    {
        mItems = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("CreateListAdapter","onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_create,parent,false);
        return new CreateListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("CreateListAdapter","onBindViewHolder");
        Item item = mItems.get(position);
        Log.i("CreateListAdapter","Desc: " + item.getDescription() + ", Quantity: " + item.getQuantity());
        CreateListViewHolder listHolder = (CreateListViewHolder)holder;
        listHolder.data = item;
        listHolder.tvDesc.setText(item.getDescription());
        listHolder.tvQuantity.setText(Integer.toString(item.getQuantity()));

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }



}
