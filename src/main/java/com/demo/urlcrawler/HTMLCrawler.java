package com.demo.urlcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HTMLCrawler {
    public String getHTML(String shortURL) {
        String result = "";
        try {
            // set up an URI, as URL(String) is deprecated.
            URI uri = new URI("https://de.wikipedia.org/w/api.php?action=parse&page=" + shortURL + "&format=json");
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
            result = content.toString();
            // close the HTTP connection
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}