package com.me.phuongnd.testbilling;

import com.me.phuongnd.testbilling.utils.Commons;
/**
 * Created by phuongnd on 6/7/17.
 */

public class RequestPayment {
    String requestID;
    String requestTime;
    String agentUser;
    PaymentInfo paymentInfo;
    String signature;

    public RequestPayment(String requestID,String agentUser, PaymentInfo paymentInfo) {
        this.requestID = requestID;
        this.agentUser = agentUser;
        this.requestTime = Commons.getTime();
        this.paymentInfo = paymentInfo;
    }

    @Override
    public String toString() {
        return "{ requestID: " + this.requestID + ",agentUser:\""+ this.agentUser + "\",requestTime:\"" + this.requestTime + "\",paymentInfo:" + this.paymentInfo.toString() + ", signature:\""+ this.signature + "\"}";
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignContent(){
        StringBuilder sb = new StringBuilder();
        sb.append(requestID);
        sb.append(requestTime);
        sb.append(paymentInfo.payer);
        sb.append(paymentInfo.amount);
        sb.append(paymentInfo.email);
        sb.append(paymentInfo.mobile);
        sb.append(paymentInfo.address);
        return sb.toString();
    }

}
