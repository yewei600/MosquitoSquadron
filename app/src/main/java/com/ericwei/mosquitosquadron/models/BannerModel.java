package com.ericwei.mosquitosquadron.models;

/**
 * Created by eric on 10/10/16.
 */

public class BannerModel {
    private String id;
    private String firstname;
    private String lastname;
    private String createDate;


    public BannerModel(String id, String firstname, String lastname, String createDate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
