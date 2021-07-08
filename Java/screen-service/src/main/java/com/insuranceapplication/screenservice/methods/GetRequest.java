package com.insuranceapplication.screenservice.methods;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

abstract public class GetRequest {

    static FileReader fileReader = null;
    static String serverUrl = "http://localhost";
    static Properties properties = new Properties();

        static public void getRequest(String endpoint) {

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
}