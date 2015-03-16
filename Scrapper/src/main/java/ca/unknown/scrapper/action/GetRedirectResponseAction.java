package ca.unknown.scrapper.action;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

public class GetRedirectResponseAction extends AbstractAction{
	ScrapeTarget target;
	
	Collection<Map<String, List<String>>> headerContainer;
	
	public GetRedirectResponseAction(HtmlScrapper scraper, AttributeTarget target, Collection<Map<String, List<String>>> redirectContainer) {
		super(scraper);
		this.target = target;
		this.headerContainer = redirectContainer;
	}

	@Override
	public Action execute(){
		URL url;
		if(scraper.scrape(target)){
			try {
				URI uri = new URI(scraper.getScrapeResult().get(0));
				
				if(!uri.isAbsolute()){
					url = new URL(scraper.getBaseUri() + scraper.getScrapeResult().get(0));
				}else{
					url = new URL(uri.toString());
				}
				HttpURLConnection.setFollowRedirects(false);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				headerContainer.add(connection.getHeaderFields());
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
				return super.execute();
			}
		}
		return successCallback;
	}
}
