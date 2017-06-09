package com.me.phuongnd.testbilling;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.provider.Settings.Secure;

import com.jdi.paymentgw.PaymentService;
import com.jdi.paymentgw.utils.Common;
import com.me.phuongnd.testbilling.utils.Commons;
import com.me.phuongnd.testbilling.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {

    final Activity context = this;
    PaymentService paymentService;
    String url = "http://45.124.85.89:8111/RestPaymentWS/resources/requestPayment";
    String verifyUrl = "http://45.124.85.89:8111/RestPaymentWS/resources/verifyPayment";
    JSONObject jsonObject;
    String deviceID ;
    String tokenResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerForPayment();
        paymentService = new PaymentService(url);
    }

    Button button;


    public void addListenerForPayment() {
        button =  (Button) findViewById(R.id.button);
        try {

            deviceID = Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

            PaymentInfo paymentInfo = new PaymentInfo("nguyen dinh phuong",20000,"phuong@jdisoft.com","0988310386","Ha noi");

            RequestPayment requestPayment = new RequestPayment(deviceID,"toh",paymentInfo);

            requestPayment.setSignature(Commons.md5(requestPayment.getSignContent()+ Constant.MERCHANT_PASSWORD));

            jsonObject = new JSONObject(requestPayment.toString());
            Log.i("17",jsonObject.toString());


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AsyncTask task = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] params) {
                            return paymentService.requestPayment(jsonObject,context);
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            super.onPostExecute(o);
                            if(o!=null) {
                                // Thong bao loi neu co
                                Log.i("17",(String) o);
                            }
                        }
                    };
                    task.execute();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Sau khi hoan thanh thanh toan , ham nay tra ve ma token, su dung token nay de verify ket qua(xu ly phia server)
        if (resultCode == RESULT_OK) {
            Log.i("17",data.getStringExtra("token_code"));
            tokenResult = data.getStringExtra("token_code");
            AsyncTask task = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] params) {
                    VerifyPayment verifyPayment = new VerifyPayment(deviceID,"toh",tokenResult);
                    verifyPayment.setSignature(Commons.md5(verifyPayment.getSignContent()+Constant.MERCHANT_PASSWORD));
                    JSONObject req = null;
                    try {
                        req = new JSONObject(verifyPayment.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    com.jdi.paymentgw.server.PaymentService paymentVerifyService = new com.jdi.paymentgw.server.PaymentService(verifyUrl);
                    return paymentVerifyService.verifyPayment(req);
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    if(o!=null) {
                        // Thong bao loi neu co
                        Log.i("17","Result verify:" + (String) o);
                    }
                }
            };
            task.execute();
        }

    }


}
