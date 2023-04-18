package com.demo.urlcrawler;

import java.util.List;

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
			urlGrabber.URLextractor(crawler.getHTML(url));
			// Sleep for 1 sec to avoid being blocked
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
