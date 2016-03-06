package com.mhci4.mhci4.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

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

}
