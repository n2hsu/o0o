package com.n2hsu.android.o0o.rss;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RSSChannel {

	private String title;
	private String imageUrl;
	private String imageLocal;
	private String description;
	private String link;
	private String language;
	private String ttl;
	private String generator;
	private String copyright;
	private String pubDate;
	private String webMaster;
	private String lastBuildDate;
	private List<RSSItem> item = new ArrayList<RSSItem>();
	private URL xmlURL;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getGenerator() {
		return generator;
	}
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getWebMaster() {
		return webMaster;
	}
	public void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
	}
	public String getLastBuildDate() {
		return lastBuildDate;
	}
	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	public List<RSSItem> getItem() {
		return item;
	}
	public void setItem(List<RSSItem> item) {
		this.item = item;
	}
	public URL getXmlURL() {
		return xmlURL;
	}
	public void setXmlURL(URL xmlURL) {
		this.xmlURL = xmlURL;
	}
	public String getImageLocal() {
		return imageLocal;
	}
	public void setImageLocal(String imageLocal) {
		this.imageLocal = imageLocal;
	}
}
