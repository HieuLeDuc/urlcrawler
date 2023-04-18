package com.demo.urlcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class URLgrabber {
    private List<String> names = new ArrayList<String>();

    public void getName(String content) {
        // Convert String to JSON
        JSONObject jsonObj = new JSONObject(content);
        JSONArray jsonArray = jsonObj.getJSONObject("parse").getJSONArray("links");

        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                String name = jsonArray.getJSONObject(i).getString("*");
                filter(name);
            }
        }

        // write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (String name : names) {
                writer.write(name);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void filter(String content) {
        // Doesnt contain Kreis or contain Liste
        if ((content.toLowerCase().contains("kreis") || content.toLowerCase().contains("region"))
                && !content.toLowerCase().contains("liste") && !content.toLowerCase().contains("deutsch")
                && !content.contentEquals("Kreisstadt") && !content.contentEquals("Landkreis")
                && !content.contentEquals("Kreisfreie Stadt")) {
            content = "de.wikipedia.org/wiki/" + content.replace(" ", "_");
            System.out.println(content);
            names.add(content);
        }

    }
}
