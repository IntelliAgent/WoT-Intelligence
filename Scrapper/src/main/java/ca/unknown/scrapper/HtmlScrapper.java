package ca.unknown.scrapper;

import java.util.List;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

/**
 * 
 * @author TheCoconutChef
 *
 * Scrapper is the main object trough which web scrapping is realized. 
 * 
 * A scrapper fetches a target, which is some pattern of html, and outputs
 * either an html string or an interfacing structure for html interaction and
 * further processing. 
 * 
 * The rule is this : 
 * 	A scrapper may only interact with a web page in order to reach another web
 * 	page of fetch a target, which is ultimatly html or an interfacing structure
 * 	for html. Specifically, a scrapper may not download any content from a page.
 *
 */
public interface HtmlScrapper {
	public void setTarget(ScrapeTarget target);
	
	public void followLink(FollowLinkAction followLinkAction);
	
	public void changePage(String url);
	
	public String getCurrentPageUrl();
	
	public void addPreAction(Action preTargetAction);
	
	public void addPostAction(Action postTargetAction);
	
	public void setEntryPointAction(Action entryPointAction);
	
	public void scrapeTarget();
	
	public List<String> shallowScrape(ScrapeTarget target);
	
	public List<String> getScrapeTargetResult();
}
