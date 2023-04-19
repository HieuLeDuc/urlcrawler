package com.demo.urlcrawler;

import java.util.List;

import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlcrawlerApplication.class, args);
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
			urlGrabber.URLextractor(jsonObj.toString());
		}
	}
}
