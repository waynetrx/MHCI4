package com.mhci4.mhci4.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhci4.mhci4.R;

public class MapDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_popup,container);
        TextView popupName = (TextView)view.findViewById(R.id.popup_name);
        TextView popupDeadline = (TextView)view.findViewById(R.id.popup_task_deadline);
        TextView popupBudget = (TextView)view.findViewById(R.id.popup_budget);
        TextView popupDaysRemaining = (TextView)view.findViewById(R.id.popup_time_remain);
        TextView popupAddress = (TextView)view.findViewById(R.id.popup_address);
        return view;
    }
}
