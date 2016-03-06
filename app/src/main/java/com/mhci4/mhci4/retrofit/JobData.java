package com.mhci4.mhci4.retrofit;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobData {

    @SerializedName("grocery")
    @Expose
    private Grocery grocery;
    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();

    /**
     *
     * @return
     *     The grocery
     */
    public Grocery getGrocery() {
        return grocery;
    }

    /**
     *
     * @param grocery
     *     The grocery
     */
    public void setGrocery(Grocery grocery) {
        this.grocery = grocery;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

}
