package com.mhci4.mhci4.retrofit;

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
    private Double budget;
    @SerializedName("deadline")
    @Expose
    private String deadline;
    @SerializedName("comments")
    @Expose
    private String comments;



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
    public Double getBudget() {
        return budget;
    }

    /**
     *
     * @param budget
     *     The budget
     */
    public void setBudget(Double budget) {
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

}
