package ca.intelliagent.scrapper.action;

import ca.intelliagent.scrapper.HtmlScrapper;
import ca.intelliagent.scrapper.scrapeTarget.AttributeTarget;
import ca.intelliagent.scrapper.scrapeTarget.ScrapeTarget;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class GetRedirectResponseAction extends AbstractAction{
	ScrapeTarget target;
	
	Collection<Map<String, List<String>>> headerContainer;
	
	public GetRedirectResponseAction(HtmlScrapper scraper, AttributeTarget target, Collection<Map<String, List<String>>> redirectContainer) {
		super(scraper);
		this.target = target;
		headerContainer = redirectContainer;
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
