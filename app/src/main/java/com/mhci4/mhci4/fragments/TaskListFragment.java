package com.mhci4.mhci4.fragments;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mhci4.mhci4.R;
import com.mhci4.mhci4.library.CreateListAdapter;
import com.mhci4.mhci4.library.ErrandListAdapter;
import com.mhci4.mhci4.retrofit.APIAsyncTask;
import com.mhci4.mhci4.retrofit.Grocery;
import com.mhci4.mhci4.retrofit.RetrofitHandler;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskListFragment extends Fragment implements RetrofitHandler.RetrofitCallback
{
    RecyclerView recyclerView;
    List<Grocery> groceries;
    public TaskListFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_task_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        APIAsyncTask apiAsyncTask = new APIAsyncTask(RetrofitHandler.RESULT_RETRIEVE_ALL_JOBS,this);
        apiAsyncTask.execute();
        groceries = new ArrayList<Grocery>();
        recyclerView = (RecyclerView)view.findViewById(R.id.list_errands);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);




    }

    @Override
    public void onResponse(int resultCode, boolean result, Object data) {
        switch(resultCode)
        {
            case RetrofitHandler.RESULT_RETRIEVE_ALL_JOBS:
                if(data != null)
                {
                    groceries = (List<Grocery>) data;
                    ErrandListAdapter mAdapter = new ErrandListAdapter(getContext(),groceries);
                    recyclerView.setAdapter(mAdapter);
                   /* List<Grocery>groceries = (List<Grocery>) data;
                    for(Grocery g : groceries)
                    {

                    }*/
                }
                break;
            default:
                break;
        }
    }


    /*public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 3.0f;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2.0f;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }*/
}
