package ca.unknown.scrapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

/**
 * Jsoup based implementation of scrapper interface
 *
 */
public class JsoupHtmlScrapper implements HtmlScrapper {
	
	private static final int TIMEOUT = 5000;
	
	private String currentPageUrl;
	
	private Document currentPage;
	
	private List<String> scrapeResult = new ArrayList<String>();
	
	List<String> shallowScrapeResult;
		
	private List<Action> preTargetActions = new ArrayList<Action>();
	
	private List<Action> postTargetActions = new ArrayList<Action>();
	
	private Action entryPointAction;
	
	/**
	 * Empty JsoupScrapper constructor
	 */
	public JsoupHtmlScrapper(String entryPoint){
		changePage(entryPoint);
	}

	@Override
	public void addPreAction(Action preTargetAction) {
		this.preTargetActions.add(preTargetAction);
	}

	@Override
	public void addPostAction(Action postTargetAction) {
		this.postTargetActions.add(postTargetAction);
	}

	@Override
	public void setEntryPointAction(Action entryPointAction) {
		this.entryPointAction = entryPointAction;
	}

	@Override
	public void scrape(ScrapeTarget target) {		
		Elements selection = currentPage.select(target.getSelectString());
		
		for(Element elem : selection)
			scrapeResult.add(target.retrieveTarget(elem));
	}

	@Override
	public void followLink(FollowLinkAction followLinkAction) {
		followLinkAction.execute();		
	}

	@Override
	public void changePage(String url) {
		currentPageUrl = url;
		try{			
			currentPage = Jsoup.parse(new URL(url),TIMEOUT);
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
	public List<String> getShallowScrapeResult(){
		return shallowScrapeResult;
	}

	@Override
	public String getCurrentPageUrl() {
		return currentPageUrl;
	}

	@Override
	public boolean shallowScrape(ScrapeTarget target) {
		shallowScrapeResult = new ArrayList<String>();
		
		Elements selection = currentPage.select(target.getSelectString());
		
		for(Element elem : selection)
			shallowScrapeResult.add(target.retrieveTarget(elem));
		
		return !shallowScrapeResult.isEmpty();
	}
	
	@Override
	public void clearScrapeResult() {
		scrapeResult.clear();
	}
	
	private void executeActions(List<Action> actions){
		for(Action action : actions)
			action.execute();
	}
}
