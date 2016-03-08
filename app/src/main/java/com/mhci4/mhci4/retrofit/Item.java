package com.mhci4.mhci4.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mhci4.mhci4.MainActivity;

public class Item {

    @SerializedName("iid")
    @Expose
    private Integer iid;
    @SerializedName("jid")
    @Expose
    private Integer jid;
    @SerializedName("sequence")
    @Expose
    private Integer sequence;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("status")
    @Expose
    private Integer status;

    public Item()
    {}

    public Item(String description,int quantity)
    {
        this.iid = MainActivity.itemId.incrementAndGet();
        this.description = description;
        this.quantity = quantity;
    }


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
     *     The sequence
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     *
     * @param sequence
     *     The sequence
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     *     The quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIid() {
        return iid;
    }

    public void setIid(Integer iid) {
        this.iid = iid;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Item)
        {
            Item item = (Item)o;
            if(iid != null)
            {
                return this.iid.compareTo(item.getIid()) == 0;
            }

        }
        return false;
    }
}
