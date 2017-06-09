package com.jdi.paymentgw;

import android.app.Activity;
import android.content.Intent;

import com.jdi.paymentgw.utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by phuongnd on 6/6/17.
 */

public class PaymentService implements IPaymentService {

    String url;
    private Intent intentCheckout;

    public PaymentService(String url) {
        this.url = url;
    }

    @Override
    public String requestPayment(JSONObject order, Activity context) {
        try {
            URL wsUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(order.toString());
            out.close();

            if (conn.getResponseCode() != 200) {
                return "{ response_code:\"" + conn.getResponseCode() + "\",response_detail:\"Failed : HTTP error code : \"}" ;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            StringBuilder result = new StringBuilder();
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result.append(output);
            }
            conn.disconnect();
            JSONObject objResult = new JSONObject(result.toString());
            String responseCode = objResult.getString("response_code");
            if (responseCode.equalsIgnoreCase("00")) {
                String tokenCode = objResult.getString("token_code");
                String checkoutUrl = objResult.getString("checkout_url");

                intentCheckout = new Intent(context,JDIPaymentActivity.class);
                intentCheckout.putExtra(JDIPaymentActivity.TOKEN_CODE, tokenCode);
                intentCheckout.putExtra(JDIPaymentActivity.CHECKOUT_URL, checkoutUrl);
                context.startActivityForResult(intentCheckout,1000);
            } else {
                if(!"".equals("response_detail")) return result.toString();
                else return "{ response_code:\"" + responseCode + "\",response_detail:\"" + Common.getCodeError(context,responseCode) +"\"}" ;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void verifyPayment(JSONObject order) {

    }


}
