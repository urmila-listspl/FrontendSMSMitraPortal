package com.list20.SMSMitraPortal.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class BranchMaster {
    @Id
    private String branchCode;
    private String bankCode;
    private String branchName;
    private String branchAddress;
    private String cityName;
    private String State;
    private int zipcode;
    private String contactNumber;
    private String email;

    public String getBranchCode() {
        return this.branchCode;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public String getBranchAddress() {
        return this.branchAddress;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getState() {
        return this.State;
    }

    public int getZipcode() {
        return this.zipcode;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    public void setBranchAddress(final String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public void setState(final String State) {
        this.State = State;
    }

    public void setZipcode(final int zipcode) {
        this.zipcode = zipcode;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public BranchMaster() {
    }
}
