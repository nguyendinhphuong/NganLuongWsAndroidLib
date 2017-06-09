package com.me.phuongnd.testbilling;

import com.me.phuongnd.testbilling.utils.Commons;

/**
 * Created by phuongnd on 6/8/17.
 */

public class VerifyPayment {
    String requestID;
    String requestTime;
    String agentUser;
    String token;
    String signature;

    public VerifyPayment(String requestID, String agentUser, String token) {
        this.requestID = requestID;
        this.requestTime = Commons.getTime();
        this.agentUser = agentUser;
        this.token = token;
    }


    @Override
    public String toString() {
        return "{ requestID: " + this.requestID + ",agentUser:\""+ this.agentUser + "\",requestTime:\"" + this.requestTime + "\",token:\"" + this.token + "\", signature:\""+ this.signature + "\"}";
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignContent(){
        StringBuilder sb = new StringBuilder();
        sb.append(requestID);
        sb.append(requestTime);
        sb.append(agentUser);
        sb.append(token);
        return sb.toString();
    }
}
