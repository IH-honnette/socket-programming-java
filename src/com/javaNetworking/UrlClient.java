package com.javaNetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlClient {
    public static void main(String[] args) throws IOException {

        try {
            URL url = new URL("https://igihe.com/");
            URLConnection connectRemote = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connectRemote.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
