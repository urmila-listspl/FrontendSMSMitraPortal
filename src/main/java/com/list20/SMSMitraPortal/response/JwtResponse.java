package com.list20.SMSMitraPortal.response;

public class JwtResponse {
    public String token;
    public String bankName;
    public String branchName;
    public byte[] bankLogo;

    public JwtResponse(String token, String bankName, String branchName, byte[] bankLogo) {
        this.token = token;
        this.bankName = bankName;
        this.branchName = branchName;
        this.bankLogo = bankLogo;
    }

    public String getToken() {
        return this.token;
    }

    public String getBankName() {
        return this.bankName;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public byte[] getBankLogo() {
        return this.bankLogo;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    public void setBankLogo(final byte[] bankLogo) {
        this.bankLogo = bankLogo;
    }
}
