package ca.unknown.scrapper;

import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
/**
 * Jsoup based implementation of scrapper interface
 */
public class JsoupHtmlScrapper implements HtmlScrapper {
	
	private static final int TIMEOUT = 50000;
	
	private URI currentPageUrl;
	
	private Document currentPage;
	
	private List<String> scrapeResult = new ArrayList<String>();
			
	
	/**
	 * Empty JsoupScrapper constructor
	 */
	public JsoupHtmlScrapper(String entryPoint){
		changePage(entryPoint);
	}

	@Override
	public boolean scrape(ScrapeTarget target) {		
		scrapeResult.clear();
		
		Elements selection = currentPage.select(target.getSelectString());
		
		for(Element elem : selection)
			scrapeResult.add(target.retrieveTarget(elem));
		
		return !scrapeResult.isEmpty();
	}

	@Override
	public void changePage(String url) {
		try {
			currentPageUrl = new URI(url);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!currentPageUrl.isAbsolute())
			try {
				currentPageUrl = new URI(currentPage.baseUri() + url);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		System.out.println("Going to " + currentPageUrl);
		try{
			currentPage = Jsoup.parse(currentPageUrl.toURL(),TIMEOUT);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}

	@Override
	public List<String> getScrapeResult() {
		return scrapeResult;
	}

	@Override
	public String getCurrentPageUrl() {
		return currentPageUrl.toString();
	}
	
	@Override
	public void clearScrapeResult() {
		scrapeResult.clear();
	}

	@Override
	public String getBaseUri() {
		return currentPage.baseUri();
	}
}
