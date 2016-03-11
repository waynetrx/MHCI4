package com.mhci4.mhci4.fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mhci4.mhci4.R;
import com.mhci4.mhci4.library.CreateListAdapter;
import com.mhci4.mhci4.library.MyLocation;
import com.mhci4.mhci4.retrofit.APIAsyncTask;
import com.mhci4.mhci4.retrofit.Grocery;
import com.mhci4.mhci4.retrofit.Item;
import com.mhci4.mhci4.retrofit.JobData;
import com.mhci4.mhci4.retrofit.RetrofitHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskMapFragment extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener,RetrofitHandler.RetrofitCallback
{
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    View mapMarker;
    CircleImageView circleImageView;
    List<Grocery> groceries;
    Grocery mGrocery;
    public TaskMapFragment()
    {
        groceries = new ArrayList<Grocery>();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_task_map,container,false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Instantiate and declare views
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.task_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);

        Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.profile_pic);

  /*      locations = new ArrayList<MyLocation>();
        MyLocation location = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xta1/t51.2885-19/11820706_1003519006359959_805015462_a.jpg",new LatLng(1.442231,103.785828));
        MyLocation location2 = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/s150x150/12717098_1195989490424375_783062561_a.jpg",new LatLng(1.442176,103.791178));
        MyLocation location3 = new MyLocation("https://igcdn-photos-d-a.akamaihd.net/hphotos-ak-xta1/t51.2885-19/s150x150/11950584_437428289790419_817053692_a.jpg",new LatLng(1.440310,103.777016));
        MyLocation location4 = new MyLocation("https://igcdn-photos-f-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/s150x150/12725163_958047127583149_1424841141_a.jpg",new LatLng(1.439452,103.785471));
        MyLocation location5 = new MyLocation("https://igcdn-photos-g-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-19/s150x150/12724858_1217549054939534_668525612_a.jpg",new LatLng(1.445951,103.779055));
        MyLocation location6 = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xpf1/t51.2885-19/s150x150/12552407_241753659490143_620404806_a.jpg", new LatLng(1.438765,103.784763));
        MyLocation location7 = new MyLocation("http://oi64.tinypic.com/s0wvg4.jpg",new LatLng(1.435912,103.779870));
        MyLocation location8 = new MyLocation("http://oi65.tinypic.com/2yjzfol.jpg",new LatLng(1.436127,103.782037));
  */

        APIAsyncTask apiAsyncTask = new APIAsyncTask(RetrofitHandler.RESULT_RETRIEVE_ALL_JOBS,this);
        apiAsyncTask.execute();
    }

    public void showDialog(Grocery grocery,JobData jobData)
    {
        List<Item> items;
        CreateListAdapter mAdapter;
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.fragment_dialog_popup);
        TextView tvName = (TextView)dialog.findViewById(R.id.popup_name);
        TextView tvBudget = (TextView)dialog.findViewById(R.id.popup_budget);
        TextView tvAddress = (TextView)dialog.findViewById(R.id.popup_address);
        CircleImageView imageView = (CircleImageView)dialog.findViewById(R.id.popup_profile);

        RecyclerView recyclerView = (RecyclerView)dialog.findViewById(R.id.popup_list_grocery);
        items = new ArrayList<Item>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new CreateListAdapter(getContext(),jobData.getItems());
        recyclerView.setAdapter(mAdapter);

        tvName.setText(grocery.getUser().getName());
        tvAddress.setText(grocery.getAddress());
        tvBudget.setText("$" + jobData.getGrocery().getBudget());
        Log.i("BuDGET","Budget: " + jobData.getGrocery().getBudget());
        Glide.with(getContext()).load(grocery.getUser().getImage()).into(imageView);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        boolean match = false;
        for(Grocery g : groceries)
        {
            if(g.equals(marker.getTitle()))
            {
                mGrocery = g;
                match = true;
                continue;
            }
        }
        if(match)
        {
            APIAsyncTask apiAsyncTask = new APIAsyncTask(RetrofitHandler.RESULT_RETRIEVE_JOB,this);
            apiAsyncTask.setJobID(mGrocery.getJid());
            apiAsyncTask.execute();
            //showDialog(grocery);
        }

        return false;
    }

    @Override
    public void onResponse(int resultCode, boolean result, Object data) {
        switch(resultCode)
        {
            case RetrofitHandler.RESULT_RETRIEVE_ALL_JOBS:
                if(data != null)
                {
                    groceries = (List<Grocery>) data;
                    // Initialize marker inflater for view placeholder to load images
                    mapMarker = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
                    circleImageView = (CircleImageView) mapMarker.findViewById(R.id.marker_profile_image);
                    ImageTask imageTask = new ImageTask(mapMarker,circleImageView,groceries);
                    imageTask.execute();
                }
                break;
            case RetrofitHandler.RESULT_RETRIEVE_JOB:
                if(data!=null)
                {
                    JobData jobData = (JobData)data;
                    showDialog(mGrocery,jobData);
                }

                break;
            default:
                break;
        }
    }

    public class ImageTask extends AsyncTask<Void,Void,Void>
    {

        private Bitmap bmp;
        CircleImageView imageView;
        private List<Grocery> groceries;
        View mapMarker;
        public ImageTask(View mapMarker,CircleImageView imageView,List<Grocery> groceries)
        {
            this.imageView = imageView;
            this.groceries = groceries;
            this.mapMarker = mapMarker;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Looper.prepare();
            try {

                for(int i = 0; i < groceries.size(); i++)
                {
                    groceries.get(i).setBmp(Glide.with(getContext()).load(groceries.get(i).getUser().getImage()).asBitmap().into(-1,-1).get());
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
//                    locations.get(i).setBmp(Glide.with(getContext()).load(locations.get(i).getImageLink()).asBitmap().into(-1,-1).get());


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            for(int i = 0; i < groceries.size();i++)
            {
                mapMarker = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
                circleImageView = (CircleImageView) mapMarker.findViewById(R.id.marker_profile_image);
                circleImageView.setImageBitmap(groceries.get(i).getBmp());
                LatLng point = new LatLng(groceries.get(i).getLat(),groceries.get(i).getLng());
                Marker marker = mMap.addMarker(new MarkerOptions().position(point).title("Marker at Republic Poly")
                        .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getContext(),mapMarker))));
                marker.setTitle(groceries.get(i).getJid().toString());
                groceries.get(i).setMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));
            }



            super.onPostExecute(aVoid);
        }
    }

    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }


    }
