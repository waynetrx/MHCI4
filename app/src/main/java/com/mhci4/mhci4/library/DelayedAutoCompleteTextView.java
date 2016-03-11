package com.mhci4.mhci4.library;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;


public class DelayedAutoCompleteTextView extends AutoCompleteTextView{

    private static final int DEFAULT_AUTOCOMPLETE = 750;
    private static final int MESSAGE_TEXT_CHANGED = 100;

    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE;

    private final Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            DelayedAutoCompleteTextView.super.performFiltering((CharSequence)msg.obj, msg.arg1);
        }
    };

    public DelayedAutoCompleteTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode)
    {
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TEXT_CHANGED,text),mAutoCompleteDelay);
    }

    @Override
    public void onFilterComplete(int count) {
        super.onFilterComplete(count);
    }
}
