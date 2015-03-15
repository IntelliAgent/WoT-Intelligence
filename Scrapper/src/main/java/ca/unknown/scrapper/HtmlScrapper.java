package ca.unknown.scrapper;

import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

import java.util.List;

/**
 * @author TheCoconutChef
 *         
 *         Scrapper is the main object trough which web scrapping is realized.
 *         
 *         A scrapper may only interact with a web page in order to reach another web
 *         page of fetch a target, which is ultimatly html or an interfacing structure
 *         for html. Specifically, a scrapper may not download any content from a page.
 */
public interface HtmlScrapper {	
	public void followLink(FollowLinkAction followLinkAction);
	
	public void changePage(String url);
	
	public String getCurrentPageUrl();
		
	public boolean scrape(ScrapeTarget target);
			
	public List<String> getScrapeResult();
	
	public void clearScrapeResult();
}
