package com.mhci4.mhci4;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.mhci4.mhci4.gcm.GCMHandler;
import com.mhci4.mhci4.library.CreateListAdapter;
import com.mhci4.mhci4.library.GroceryItem;
import com.mhci4.mhci4.retrofit.RetrofitHandler;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TextWatcher,TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener,RetrofitHandler.RetrofitCallback {

    EditText etBudget;
    TextView etDeadline;
    private SwipeMenuListView mListView;
    private CreateListAdapter mAdapter;
    ArrayList<GroceryItem> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBudget = (EditText) findViewById(R.id.et_budget_create);
        etDeadline = (TextView) findViewById(R.id.et_calendar_create);
        mListView = (SwipeMenuListView) findViewById(R.id.list_grocery_create);

        Log.i("MainActivity",",main activity calling");
        GCMHandler gcmHandler = new GCMHandler(getApplicationContext());
        gcmHandler.initialize();

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

        items = new ArrayList<GroceryItem>();
        items.add(new GroceryItem(5,"Cabbage"));
        items.add(new GroceryItem(2,"Pork"));
        items.add(new GroceryItem(5,"Cabbage"));
        items.add(new GroceryItem(2,"Pork"));
        items.add(new GroceryItem(5,"Cabbage"));
        items.add(new GroceryItem(2,"Pork"));
        items.add(new GroceryItem(5,"Cabbage"));
        items.add(new GroceryItem(2,"Pork"));
        items.add(new GroceryItem(5,"Cabbage"));
        items.add(new GroceryItem(2,"Pork"));
        mAdapter = new CreateListAdapter(getApplicationContext(),items);
        mListView.setAdapter(mAdapter);

        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(90));
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.BLACK);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch(index)
                {
                    case 0:
                        break;
                    case 1:
                        items.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
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

    }
}