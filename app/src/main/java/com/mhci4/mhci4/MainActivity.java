package com.mhci4.mhci4;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.dd.processbutton.iml.ActionProcessButton;
import com.mhci4.mhci4.gcm.GCMHandler;
import com.mhci4.mhci4.library.CreateListAdapter;
import com.mhci4.mhci4.retrofit.APIAsyncTask;
import com.mhci4.mhci4.retrofit.Item;
import com.mhci4.mhci4.retrofit.JobData;
import com.mhci4.mhci4.retrofit.RetrofitHandler;
import com.mhci4.mhci4.retrofit.User;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import android.support.design.widget.Snackbar;

import co.dift.ui.SwipeToAction;

public class MainActivity extends AppCompatActivity implements TextWatcher,TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener,RetrofitHandler.RetrofitCallback, View.OnClickListener {

    public static AtomicInteger itemId = new AtomicInteger(0);

    EditText etBudget;
    TextView etDeadline;
    private SwipeMenuListView mListView;
    private CreateListAdapter mAdapter;
    List<Item> items;
    /*Button btnAddItem;
    Button btnSaveGrocery;*/
    ActionProcessButton btnSignIn;
    RecyclerView recyclerView;
    SwipeToAction swipeToAction;
    RetrofitHandler.RetrofitCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBudget = (EditText) findViewById(R.id.et_budget_create);
        etDeadline = (TextView) findViewById(R.id.et_calendar_create);
        callback = this;
        /*btnAddItem = (Button)findViewById(R.id.btn_create_add_item);
        btnAddItem.setOnClickListener(this);
        btnSaveGrocery = (Button)findViewById(R.id.btn_create_save);
        btnSaveGrocery.setOnClickListener(this);*/
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSaveGrocery);
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickListener(this);

        items = new ArrayList<Item>();
        recyclerView = (RecyclerView)findViewById(R.id.list_grocery_create);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        populate();
        mAdapter = new CreateListAdapter(getApplicationContext(),items);
        recyclerView.setAdapter(mAdapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Item>()
        {

            @Override
            public boolean swipeLeft(final Item itemData) {
                final int position = removeItem(itemData);
                displaySnackbar(itemData.getDescription() + " removed", "Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addItemAtPos(position,itemData);
                    }
                });
                return true;
            }

            @Override
            public boolean swipeRight(Item itemData) {
                Log.i("SWIPELEFT","Desc: " + itemData.getDescription());
                return true;
            }

            @Override
            public void onClick(Item itemData) {
            }

            @Override
            public void onLongClick(Item itemData) {

            }
        });

        // use swipeLeft or swipeRight and the elem position to swipe by code
    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToAction.swipeRight(2);
            }
        }, 3000);*/


        Log.i("MainActivity",",main activity calling");
        GCMHandler gcmHandler = new GCMHandler(getApplicationContext());
        gcmHandler.initialize();

        /*APIAsyncTask aat = new APIAsyncTask(RetrofitHandler.RESULT_RETRIEVE_JOB,this);
        aat.setJobID(2);
        aat.execute();*/

        etBudget.setRawInputType(Configuration.KEYBOARD_12KEY);
        etBudget.addTextChangedListener(this);

        etDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("EditText", "Calendar et clicked!");
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.setMinDate(now);
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });



    }

    private void populate()
    {
        Item item1 = new Item("Flour",4);
        Item item2 = new Item("Oyster",2);
        Item item3 = new Item("Tartar",1);
        Item item4 = new Item("Pickels",3);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        /*addItem(item1);
        addItem(item2);
        addItem(item3);
        addItem(item4);*/

    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorGray));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    private int removeItem(Item item)
    {
        int pos = items.indexOf(item);
        items.remove(pos);
        mAdapter.notifyItemRemoved(pos);
        return pos;
    }
    private void addItemAtPos(int position, Item item)
    {
        items.add(position,item);
        mAdapter.notifyItemInserted(position);
    }

    private void addItem(Item item)
    {
        items.add(item);
        mAdapter.notifyItemInserted(items.size()-1);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$")) {
            String userInput = "" + s.toString().replaceAll("[^\\d]", "");
            StringBuilder cashAmountBuilder = new StringBuilder(userInput);

            while (cashAmountBuilder.length() > 3 && cashAmountBuilder.charAt(0) == '0') {
                cashAmountBuilder.deleteCharAt(0);
            }
            while (cashAmountBuilder.length() < 3) {
                cashAmountBuilder.insert(0, '0');
            }
            cashAmountBuilder.insert(cashAmountBuilder.length() - 2, '.');
            cashAmountBuilder.insert(0, '$');

            etBudget.setText(cashAmountBuilder.toString());
            // keeps the cursor always to the right
            Selection.setSelection(etBudget.getText(), cashAmountBuilder.toString().length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM, yyyy");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.YEAR, year);
        etDeadline.setText(sdf.format(cal.getTime()));
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {

    }


    @Override
    public void onResponse(int resultCode, boolean result, Object data) {

        switch(resultCode)
        {
            case RetrofitHandler.RESULT_RETRIEVE_USER_PROFILE:
                User user = (User)data;
                Log.i("MainActivity",user.getAddress());
                break;
            case RetrofitHandler.RESULT_RETRIEVE_JOB:
                JobData jd = (JobData) data;
                for(Item item : jd.getItems())
                {
                    Log.i("MainActivity","ID: " + item.getIid() + ", Desc: " + item.getDescription());
                }
                btnSignIn.setProgress(100);
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            /*case R.id.btn_create_add_item:
                items.add(new Item("wtf",5));
                mAdapter.notifyItemInserted(items.size()-1);
                break;*/
            /*case R.id.btn_create_save:
                Intent intent = new Intent(getApplicationContext(),JobsActivity.class);
                startActivity(intent);
                break;*/
            case R.id.btnSaveGrocery:
                Intent intent = new Intent(getApplicationContext(),JobsActivity.class);
                startActivity(intent);
                /*btnSignIn.setProgress(1);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        APIAsyncTask task = new APIAsyncTask(RetrofitHandler.RESULT_RETRIEVE_JOB,callback);
                        task.setJobID(2);
                        task.execute();
                    }
                }, 3000);*/


                break;
            default:
                break;
        }

    }
}