package com.example.logeshwa.knitv1.database;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by logeshwa on 10/16/2016.
 */

public class ContactModel {

    private String ID;
    private String firstName;
    private String lastName;
    private String companyName;
    private String companyType;
    private String phoneNo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {


        this.companyName = companyName;
    }
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        //Log.d(TAG, "setCompanyName: " + firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    private String emailAdd;

}
