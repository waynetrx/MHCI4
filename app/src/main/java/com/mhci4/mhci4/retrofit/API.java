package com.mhci4.mhci4.retrofit;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("jobs_data")
    @Expose
    private List<Grocery> jobsData = new ArrayList<Grocery>();
    @SerializedName("job_data")
    @Expose
    private JobData jobData;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     *     The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     *
     * @param error
     *     The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     *
     * @return
     *     The jobsData
     */
    public List<Grocery> getJobsData() {
        return jobsData;
    }

    /**
     *
     * @param jobsData
     *     The jobs_data
     */
    public void setJobsData(List<Grocery> jobsData) {
        this.jobsData = jobsData;
    }

    /**
     *
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     *     The user
     */
    public void setUser(User user) {
        this.user = user;
    }

}

