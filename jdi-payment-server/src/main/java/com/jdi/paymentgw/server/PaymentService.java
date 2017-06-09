package com.jdi.paymentgw.server;

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

    public PaymentService(String url) {
        this.url = url;
    }

    @Override
    public String verifyPayment(JSONObject order) {
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
//            String responseCode = objResult.getString("response_code");
//            objResult.put("response_detail", ErrorCode.valueOf(responseCode));
            return objResult.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ErrorCode.GW001.toString();
    }

}