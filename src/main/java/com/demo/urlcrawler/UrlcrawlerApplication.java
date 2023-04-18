package com.demo.urlcrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UrlcrawlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlcrawlerApplication.class, args);
		HTMLCrawler crawler = new HTMLCrawler();
		URLgrabber grabber = new URLgrabber();
		grabber.getName(crawler.getHTML("Liste_der_Landkreise_in_Deutschland"));
	}
}
