package com.mhci4.mhci4.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gcm_token")
    @Expose
    private String gcmToken;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     *
     * @return
     *     The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     *     The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     *     The gcmToken
     */
    public String getGcmToken() {
        return gcmToken;
    }

    /**
     *
     * @param gcmToken
     *     The gcm_token
     */
    public void setGcmToken(String gcmToken) {
        this.gcmToken = gcmToken;
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
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
