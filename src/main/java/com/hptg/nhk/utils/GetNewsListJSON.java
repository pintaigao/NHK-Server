package com.hptg.nhk.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetNewsListJSON {
    public static String getURLContent(String urlStr){    //请求的url
        URL url = null;

        //建立的http链接
        HttpURLConnection httpConn = null;

        //请求的输入流
        BufferedReader in = null;

        //输入流的缓冲
        StringBuffer sb = new StringBuffer();

        try {
            url = new URL(urlStr);

            in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));

            String str = null;

            //一行一行进行读入
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }

        } catch (Exception ex) {
            System.err.println(ex);
        } finally {
            try {
                if (in != null) {
                    in.close(); //关闭流
                }
            } catch (IOException ex) {

            }
        }
        return sb.toString();
    }

}
