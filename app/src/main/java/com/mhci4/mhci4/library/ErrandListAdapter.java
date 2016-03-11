package com.mhci4.mhci4.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhci4.mhci4.R;
import com.mhci4.mhci4.retrofit.Grocery;

import java.util.List;

public class ErrandListAdapter extends RecyclerView.Adapter<ErrandListAdapter.ErrandListViewHolder>{
    List<Grocery> groceries;
    Context context;
    public ErrandListAdapter(Context context,List<Grocery> groceries)
    {
        this.context = context;
        this.groceries = groceries;
    }

    public static class ErrandListViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDestination;
        TextView tvBudget;
        TextView tvItemCount;
        TextView tvDeadline;
        ImageView imageProfile;
        public ErrandListViewHolder(View itemView) {
            super(itemView);
            tvDestination = (TextView)itemView.findViewById(R.id.list_errand_address);
            tvBudget = (TextView)itemView.findViewById(R.id.list_errand_budget);
            tvItemCount = (TextView)itemView.findViewById(R.id.list_errand_itemcount);
            tvDeadline = (TextView)itemView.findViewById(R.id.list_errand_deadline);
            imageProfile = (ImageView)itemView.findViewById(R.id.list_errand_image);
        }
    }

    @Override
    public ErrandListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_errand,parent,false);
        ErrandListViewHolder evh = new ErrandListViewHolder(view);
        return evh;
    }

    @Override
    public void onBindViewHolder(ErrandListViewHolder holder, int position) {
        Grocery grocery = groceries.get(position);
        holder.tvDeadline.setText(grocery.getDeadline());
        holder.tvDestination.setText(grocery.getAddress());
        holder.tvBudget.setText(Float.toString(grocery.getBudget()));
        holder.tvItemCount.setText(Integer.toString(2));
        Glide.with(context).load(grocery.getUser().getImage()).into(holder.imageProfile);
    }




    @Override
    public int getItemCount() {
        return groceries.size();
    }
}
