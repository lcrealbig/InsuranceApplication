package com.insuranceapplication.userservice.connectToServer;

import com.insuranceapplication.userservice.mainInterface.enums.LoginStatus;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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

    public LoginStatus postRequest(String endpoint) {

        HttpURLConnection huc = null;
        URL url = null;
        try {
            fileReader = new FileReader("./src/main/resources/application.properties");
            properties.load(fileReader);
            String gotowyUrl = serverUrl + ":" + properties.getProperty("user-service.port") + "/" + endpoint;
            url = new URL(gotowyUrl);
        } catch (MalformedURLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
                    // If server output is 404 which means a wrong input, you need to login again, no  limit of tries yet.
                    if (line.indexOf("404") > 0)
                        return LoginStatus.NOT_lOGGED_IN;
                }
            } catch (ConnectException ex) {
                return LoginStatus.ERROR;
            } catch (Exception ex) {
                System.out.println(ex);
                return LoginStatus.ERROR;
            } finally {
                huc.disconnect();
            }

            // Output the content to the console
            //System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LoginStatus.LOGGED_IN;
    }
}

