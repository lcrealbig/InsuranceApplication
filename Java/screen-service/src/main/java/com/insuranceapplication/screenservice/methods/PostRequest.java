package com.insuranceapplication.screenservice.methods;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;

abstract public class PostRequest {

    static FileReader fileReader = null;
    static String serverUrl = "http://localhost";
    static Properties properties = new Properties();
    private static HttpURLConnection huc = null;
    private static URL url = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static String endpoint = "";
    protected static byte[] input = null;
    private static String serviceName = null;
    /* DO WYWYALENIA? */
//    static public String parseToJson(Object body){
//        return new Gson().toJson(body);
//    }




    public static void send(Object body) {
        //connection config
        httpPreConnection();
        try {
            //preparing body for request
            byte[] input = new Gson().toJson(body).getBytes("utf-8");
            //create connection with body inside
            httpConnection(input);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Get response from service
        InputStreamReader response = (InputStreamReader) getResponse();
    }

    private static void httpPreConnection() {
        String servicePort = "";

        try {
            fileReader = new FileReader("./src/main/resources/application.properties");
            properties.load(fileReader);
            switch (serviceName) {
                case "policy-service":
                    servicePort = properties.getProperty("policy-service.port");
                    break;
            }
            String readyUrl = serverUrl + ":" + servicePort + "/" + endpoint;
            url = new URL(readyUrl);
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Content-Type", "application/json; utf-8");
            huc.setDoOutput(true);
            outputStream = huc.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private static void httpConnection(byte[] body) {
        try {
            outputStream.write(body, 0, body.length);
            outputStream.close();
            huc.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getResponse() {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(huc.getInputStream());
            huc.disconnect();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return inputStreamReader;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static void setEndpoint(String endpoint) {
        PostRequest.endpoint = endpoint;
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static void setServiceName(String serviceName) {
        PostRequest.serviceName = serviceName;
    }

    public static void setServerUrl(String serverUrl) {
        PostRequest.serverUrl = serverUrl;
    }
}
