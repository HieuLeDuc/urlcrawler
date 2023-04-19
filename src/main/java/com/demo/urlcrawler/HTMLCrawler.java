package com.demo.urlcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONObject;

//Download the response and store it as a String.
public class HTMLCrawler {
    public JSONObject getHTML(String shortURL) {
        JSONObject jsonObj = null;
        try {
            // set up an URI, as URL(String) is deprecated.
            URI uri_temp = new URI("https://de.wikipedia.org/w/api.php?action=parse&page=" + shortURL + "&format=json");
            String temp = uri_temp.toASCIIString();
            URI uri = new URI(temp);
            URL url = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // send GET request.
            connection.setRequestMethod("GET");
            String line = "";
            // the response is in JSON format, storing it as a String.
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }
            jsonObj = new JSONObject(content.toString());
            // close the HTTP connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObj;
    }
}