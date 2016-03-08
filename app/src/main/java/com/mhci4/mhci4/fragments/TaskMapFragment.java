package com.mhci4.mhci4.fragments;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mhci4.mhci4.JobsActivity;
import com.mhci4.mhci4.R;
import com.mhci4.mhci4.library.MyLocation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaskMapFragment extends Fragment implements OnMapReadyCallback
{
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    View mapMarker;
    int position = 0;
    ArrayList<MyLocation> locations;
    public TaskMapFragment()
    {}


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

        Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.profile_pic);



        mapMarker = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker, null);
        CircleImageView circleImageView = (CircleImageView) mapMarker.findViewById(R.id.marker_profile_image);
        //circleImageView.setImageResource(R.drawable.profile_pic);

        locations = new ArrayList<MyLocation>();


        MyLocation location = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xta1/t51.2885-19/11820706_1003519006359959_805015462_a.jpg",new LatLng(1.442231,103.785828));
        MyLocation location2 = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-19/s150x150/12717098_1195989490424375_783062561_a.jpg",new LatLng(1.442176,103.791178));
        MyLocation location3 = new MyLocation("https://igcdn-photos-d-a.akamaihd.net/hphotos-ak-xta1/t51.2885-19/s150x150/11950584_437428289790419_817053692_a.jpg",new LatLng(1.440310,103.777016));
        MyLocation location4 = new MyLocation("https://igcdn-photos-h-a.akamaihd.net/hphotos-ak-xta1/t51.2885-19/11820706_1003519006359959_805015462_a.jpg",new LatLng(1.442231,103.785828));
        locations.add(location);
        locations.add(location2);
        locations.add(location3);
        locations.add(location4);


        for(int i = 0; i<locations.size();i++)
        {
           final int pos = i;
            Log.i("ARraylist","Pos: " + pos);
            Picasso.with(getContext()).load(locations.get(i).getImageLink())
                    .noFade()
                    .into(circleImageView, new Callback() {

                        @Override
                        public void onSuccess() {
                            int currentPos = pos;
                            Log.i("SUCCESSIMAGE","Position: " + currentPos);
                            mMap.addMarker(new MarkerOptions().position(locations.get(currentPos).getLatlngPoint()).title("Marker at Republic Poly")
                                    .icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getContext(),mapMarker))));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locations.get(currentPos).getLatlngPoint(),15));

                        }

                        @Override
                        public void onError() {
                            int currentPos = pos;
                            Log.i("SUCCESSIMAGE","Position: " + currentPos);
                        }
                    });
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
