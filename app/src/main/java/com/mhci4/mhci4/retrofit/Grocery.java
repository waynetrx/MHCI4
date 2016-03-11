package com.mhci4.mhci4.retrofit;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.android.gms.maps.model.Marker;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Grocery {

    @SerializedName("jid")
    @Expose
    private Integer jid;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("rid")
    @Expose
    private Integer rid;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("budget")
    @Expose
    private Float budget;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("user")
    @Expose
    private User user;

    private Bitmap bmp;
    private Marker marker;

    /**
     *
     * @return
     *     The jid
     */
    public Integer getJid() {
        return jid;
    }

    /**
     *
     * @param jid
     *     The jid
     */
    public void setJid(Integer jid) {
        this.jid = jid;
    }

    /**
     *
     * @return
     *     The uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     *     The uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     *     The rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     *
     * @param rid
     *     The rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     *
     * @return
     *     The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     *     The address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     *     The budget
     */
    public Float getBudget() {
        return budget;
    }

    /**
     *
     * @param budget
     *     The budget
     */
    public void setBudget(Float budget) {
        this.budget = budget;
    }

    /**
     *
     * @return
     *     The deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     *
     * @param deadline
     *     The deadline
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     *
     * @return
     *     The comments
     */
    public String getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     *     The comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bitmap getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap bmp) {
        this.bmp = bmp;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if(o instanceof Grocery)
        {
            Grocery otherGrocery = (Grocery) o;
            if(this.getJid() == otherGrocery.getJid())
            {
                result = true;
            }
        }
        else if(o instanceof String)
        {
            String jid =  (String)o;
            result = Integer.parseInt(jid) == this.getJid();
        }

        return result;
    }

}
