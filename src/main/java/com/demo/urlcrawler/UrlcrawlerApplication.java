package com.demo.urlcrawler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlcrawlerApplication.class, args);
		
		/* 		// set file name to yyyyMMDD-HH-mm
		String fileName = Instant.now().toString().replace(":", "-").replace(".", "-").substring(0, 16);

		HTMLCrawler crawler = new HTMLCrawler();

		WikiURLextractor grabber = new WikiURLextractor();
		grabber.getName(crawler.getHTML("Liste_der_Landkreise_in_Deutschland"));
		List<String> wikiURLlList = grabber.getAddresses();

		LandkreisURLextractor urlGrabber = new LandkreisURLextractor();
		for (String url : wikiURLlList) {
			// If there is a redirection, the JSON array will be empty
			// in that case, get the new URL and try again
			JSONObject jsonObj = crawler.getHTML(url);
			if (jsonObj.getJSONObject("parse").getJSONArray("categories").length() == 0) {
				WikiURLextractor newGrabber = new WikiURLextractor();
				newGrabber.getName(jsonObj);
				jsonObj = crawler.getHTML(newGrabber.getAddresses().get(0));
				newGrabber.reset();
			}
			printToFile(url, urlGrabber.URLextractor(jsonObj.toString()), fileName);
		}*/
		String link = "https://www.kreis-mettmann.de/PDF/Anfahrtsskizze.PDF?ObjSvrID=2023&ObjID=9135&ObjLa=1&Ext=PDF&WTR=1&_ts=1682592500";
		String[] linkParts = link.split("\\?");
        link = linkParts[0];
		System.out.println(link);
	}

	public static void printToFile(String name, String url, String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("Output/" + fileName + ".log", true));

			writer.write("|" + name + "|" + url + "|");
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
