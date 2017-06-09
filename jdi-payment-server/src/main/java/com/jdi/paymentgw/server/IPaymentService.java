package com.jdi.paymentgw.server;


import org.json.JSONObject;

/**
 * Created by phuongnd on 6/6/17.
 */

public interface IPaymentService {

    String verifyPayment(JSONObject order);
}
