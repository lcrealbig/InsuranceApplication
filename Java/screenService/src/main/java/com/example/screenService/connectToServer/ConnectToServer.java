package com.example.screenService.connectToServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectToServer {
        String serverUrl = "http://localhost:8081/";
        /*Poczytaj o Eureka*/

        public void getRequest(String endpoint) {
            String gotowyUrl = serverUrl + endpoint;
            HttpURLConnection huc = null;
            URL url = null;
            try {
                url = new URL(gotowyUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                //url.openConnection();
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
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        public void postRequest(String endpoint) {
            String gotowyUrl = serverUrl + endpoint;
            HttpURLConnection huc = null;
            URL url = null;
            try {
                url = new URL(gotowyUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                //url.openConnection();
                huc = (HttpURLConnection) url.openConnection();
                huc.setRequestMethod("POST");


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
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

