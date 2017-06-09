package com.me.phuongnd.testbilling.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Commons {

    public static String md5(String strMd5) {
        try {
            String password = strMd5;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            return ex.toString();
        }
    }

    public static String getTime(){
        SimpleDateFormat spd = new SimpleDateFormat(Constant.DATE_FORMAT);
        return spd.format(new Date());
    }





}
