package com.hptg.nhk.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetNewsImage {

    public static void main(String[] args) {
        String url = "https://www3.nhk.or.jp/news/html/20180920/K10011636841_1809201233_1809201237_01_03.jpg";
        byte[] btImg = getImageFromNetByUrl(url);
        if(null != btImg && btImg.length > 0){
            System.out.println("读取到：" + btImg.length + "字节");
            String fileName = "Image.jpg";
            writeImageToDisk(btImg,fileName);
        }else {
            System.out.println("没有从该链接获取到内容");
        }
    }

    /**
     * 将图片写入到磁盘
     * @param img 图片数据流
     * @param fileName 文件保存时的名称
     */

    public static void writeImageToDisk(byte[] img,String fileName){
        try {
            File file = new File("/Users/hptg/Documents/Projects/Spring/NHK_SPRING/resources"+fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入到Resource");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5*1000);
            InputStream inputStream = conn.getInputStream();
            byte[] btImg = readInputStream(inputStream);
            return btImg;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 从输入流中获取数据
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */

    private static byte[] readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inputStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inputStream.close();
        return outStream.toByteArray();

    }


}
