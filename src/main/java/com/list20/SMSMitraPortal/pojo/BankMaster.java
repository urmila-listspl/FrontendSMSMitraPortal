package com.list20.SMSMitraPortal.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Date;

@Entity
public class BankMaster {
    @Id
    private String bankCode;
    private String bankName;
    private int noOfBranches;
    private Date establishedDate;
    private String headquartersAddress;
    private String contactNumber;
    private String email;
    private String webSite;

    public String getBankCode() {
        return this.bankCode;
    }

    public String getBankName() {
        return this.bankName;
    }

    public int getNoOfBranches() {
        return this.noOfBranches;
    }

    public Date getEstablishedDate() {
        return this.establishedDate;
    }

    public String getHeadquartersAddress() {
        return this.headquartersAddress;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getWebSite() {
        return this.webSite;
    }

    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    public void setNoOfBranches(final int noOfBranches) {
        this.noOfBranches = noOfBranches;
    }

    public void setEstablishedDate(final Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public void setHeadquartersAddress(final String headquartersAddress) {
        this.headquartersAddress = headquartersAddress;
    }

    public void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setWebSite(final String webSite) {
        this.webSite = webSite;
    }

    public BankMaster() {
    }
}
