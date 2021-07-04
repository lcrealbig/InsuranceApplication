package com.insuranceapplication.screenservice.connectToServer;

import com.google.gson.Gson;

import com.insuranceapplication.screenservice.model.user.UserScreen;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Properties;

public class ConnectToServer {

    FileReader fileReader = null;
    String serverUrl = "http://localhost";
    Properties properties = new Properties();
    /*Poczytaj o Eureka*/

    public void getRequest(String endpoint) {

        HttpURLConnection huc = null;
        URL url = null;

        try {
            fileReader = new FileReader("./src/main/resources/application.properties");
            properties.load(fileReader);
            String gotowyUrl = serverUrl + ":" + properties.getProperty("server.port") + "/" + endpoint;
            url = new URL(gotowyUrl);
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");

            // To store our response
            String content;

            // Get the input stream of the connection
            try (BufferedReader input = new BufferedReader(new InputStreamReader(huc.getInputStream()))) {
                String line;
                content = "";
                while ((line = input.readLine()) != null) {
                    // Append each line of the response and separate them
                    content = content + line;
                    content = content + System.lineSeparator();
                }
            } finally {
                huc.disconnect();
            }


            // Output the content to the console
            System.out.println(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void postRequest(String endpoint, Object obj) {
        HttpURLConnection huc = null;

        URL url = null;
        try {
            fileReader = new FileReader("./src/main/resources/application.properties");
            properties.load(fileReader);
            String gotowyUrl = serverUrl + ":" + properties.getProperty("server.port") + "/" + endpoint;
            url = new URL(gotowyUrl);
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Content-Type", "application/json; utf-8");
            huc.setDoOutput(true);

            OutputStream outputStream = huc.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            byte[] input = new Gson().toJson(obj).getBytes("utf-8");


            outputStream.write(input, 0, input.length);
            outputStream.close();
            huc.connect();
            String content;

            // Get the input stream of the connection
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(huc.getInputStream());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            huc.disconnect();
        }

    }

    public void postRequest(String endpoint) {
        HttpURLConnection huc = null;

        URL url = null;
        try {
            fileReader = new FileReader("./src/main/resources/application.properties");
            properties.load(fileReader);
            String gotowyUrl = serverUrl + ":" + properties.getProperty("server.port") + "/" + endpoint;
            url = new URL(gotowyUrl);
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("POST");
            huc.setRequestProperty("Content-Type", "application/json; utf-8");
            huc.setDoOutput(true);

            OutputStream outputStream = huc.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

            byte[] input = new Gson().toJson("test").getBytes("utf-8");


            outputStream.write(input, 0, input.length);
            outputStream.close();
            huc.connect();
            String content;

            // Get the input stream of the connection
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(huc.getInputStream());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            huc.disconnect();
        }

    }


}

