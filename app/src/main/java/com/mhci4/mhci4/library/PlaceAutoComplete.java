package com.mhci4.mhci4.library;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicInteger;


public class PlaceAutoComplete implements Parcelable {
    private static AtomicInteger counterId = new AtomicInteger(0);
    private long id;
    private CharSequence placeId;
    private CharSequence desc;

    public PlaceAutoComplete(CharSequence placeId, CharSequence desc)
    {
        this.placeId = placeId;
        this.desc = desc;
        this.id = counterId.incrementAndGet();
    }
    public PlaceAutoComplete(String placeId, String desc)
    {
        this.placeId = placeId;
        this.desc = desc;
        this.id = counterId.incrementAndGet();
    }

    public PlaceAutoComplete()
    {
        // Default constructor for the initial pickup and dropoff location.
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CharSequence getPlaceId() {
        return placeId;
    }

    public void setPlaceId(CharSequence placeId) {
        this.placeId = placeId;
    }

    public CharSequence getDesc() {
        return desc;
    }

    public boolean isPlaceSet()
    {
        if(placeId == null && desc == null)
        {
            return false;
        }
        else
            return true;
    }

    public void setDesc(CharSequence desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        TextUtils.writeToParcel(this.placeId,dest,0);
        TextUtils.writeToParcel(this.desc,dest,0);
//        dest.writeParcelable(this.placeId, flags);
//        dest.writeParcelable(this.desc, flags);
    }

    protected PlaceAutoComplete(Parcel in) {
//        this.placeId = in.readParcelable(CharSequence.class.getClassLoader());
//        this.desc = in.readParcelable(CharSequence.class.getClassLoader());
        this.placeId = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
        this.desc = TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(in);
    }

    public static final Creator<PlaceAutoComplete> CREATOR = new Creator<PlaceAutoComplete>() {
        public PlaceAutoComplete createFromParcel(Parcel source) {
            return new PlaceAutoComplete(source);
        }

        public PlaceAutoComplete[] newArray(int size) {
            return new PlaceAutoComplete[size];
        }
    };
}
