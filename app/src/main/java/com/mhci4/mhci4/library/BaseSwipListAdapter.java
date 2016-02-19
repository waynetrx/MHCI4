package com.mhci4.mhci4.library;


import android.widget.BaseAdapter;

public abstract class BaseSwipListAdapter extends BaseAdapter{
    public boolean getSwipEnableByPosition(int position){
        return true;
    }

}
