package com.mhci4.mhci4.library;


public class GroceryItem {
    private int quantity;
    private String desc;

    public GroceryItem()
    {
    }

    public GroceryItem(int quantity, String desc) {
        this.quantity = quantity;
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
