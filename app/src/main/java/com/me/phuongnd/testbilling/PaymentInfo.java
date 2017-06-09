package com.me.phuongnd.testbilling;

/**
 * Created by phuongnd on 6/7/17.
 */

public class PaymentInfo {
    String payer;
    int amount;
    String email;
    String mobile;
    String address;

    public PaymentInfo(String payer, int amount, String email, String mobile, String address) {
        this.payer = payer;
        this.amount = amount;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{ payer:\""+ this.payer+"\",amount:" + this.amount + ",email:\"" + this.email + "\",mobile:\"" + this.mobile + "\",address:\""+ this.address +"\"}";
    }
}
