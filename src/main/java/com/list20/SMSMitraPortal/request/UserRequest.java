package com.list20.SMSMitraPortal.request;

public class UserRequest {
    String userName;
    String email;
    String password;
    String bankName;
    String branchName;

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    public UserRequest(final String userName, final String email, final String password, final String bankName, final String branchName) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bankName = bankName;
        this.branchName = branchName;
    }

    public UserRequest() {
    }
}
