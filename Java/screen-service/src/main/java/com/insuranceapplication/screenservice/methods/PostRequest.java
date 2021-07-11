package com.insuranceapplication.screenservice.methods;

import com.google.gson.Gson;
import com.insuranceapplication.screenservice.mainInterface.enums.ResponseType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

abstract public class PostRequest {

    private static ResponseType responseType = ResponseType.SINGLE;
    private static FileReader fileReader = null;
    private static String serverUrl = "http://localhost";
    private static Properties properties = new Properties();
    private static HttpURLConnection huc = null;
    private static URL url = null;
    private static OutputStream outputStream = null;
    private static OutputStreamWriter outputStreamWriter = null;
    private static String endpoint = "";
    private static byte[] input = null;
    private static String serviceName = null;


    public static Object send(Object body) {
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
        switch (responseType) {
            case ARRAY:
                return getResponseArray();
            default:
                return getResponse();
        }
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

    private static Object getResponseArray() {
        InputStreamReader inputStreamReader = null;
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = null;
        try {
            inputStreamReader = new InputStreamReader(huc.getInputStream());
            try {
                jsonArray = (JSONArray) jsonParser.parse(inputStreamReader);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            huc.disconnect();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return jsonArray;
    }

    private static Object getResponse() {
        InputStreamReader inputStreamReader = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            inputStreamReader = new InputStreamReader(huc.getInputStream());
            jsonObject = (JSONObject) jsonParser.parse(inputStreamReader);
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            if (e.getErrorType() == 1) {
                return null;
            }
            huc.disconnect();
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception");
        }
        return jsonObject;
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

    public static void setResponseType(ResponseType responseType) {
        PostRequest.responseType = responseType;
    }

    public static ResponseType getResponseType() {
        return responseType;
    }
}
