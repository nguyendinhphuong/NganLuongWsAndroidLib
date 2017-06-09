package com.jdi.paymentgw;


import android.app.Activity;

import org.json.JSONObject;

/**
 * Created by phuongnd on 6/6/17.
 */

public interface IPaymentService {

    String requestPayment(JSONObject order, Activity context);

    void verifyPayment(JSONObject order);
}
