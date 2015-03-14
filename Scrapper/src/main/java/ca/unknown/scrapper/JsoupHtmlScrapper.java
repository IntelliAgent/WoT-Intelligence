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
import ca.unknown.scrapper.target.Target;

/**
 * Jsoup based implementation of scrapper interface
 *
 */
public class JsoupHtmlScrapper implements HtmlScrapper {
	
	private static final int TIMEOUT = 5000;
	
	private String currentPageUrl;
	
	private Document currentPage;
	
	private List<String> scrapeResult = new ArrayList<String>();
	
	private Target target;
	
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
	public void setTarget(Target target) {
		this.target = target;
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
	public void scrapeTarget() {
		executeActions(preTargetActions);
		
		Elements selection = currentPage.select(target.getSelectString());
		
		for(Element elem : selection)
			scrapeResult.add(target.retrieveTarget(elem));
			
		executeActions(postTargetActions);
	}

	@Override
	public void followLink(FollowLinkAction followLinkAction) {
		followLinkAction.execute(this);		
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
	public List<String> getScrapeTargetResult() {
		return scrapeResult;
	}

	@Override
	public String getCurrentPageUrl() {
		return currentPageUrl;
	}
	
	private void executeActions(List<Action> actions){
		for(Action action : actions)
			action.execute(this);
	}

	@Override
	public List<String> shallowScrape(Target target) {
		List<String> shallowScrapeResult = new ArrayList<String>();
		
		Elements selection = currentPage.select(target.getSelectString());
		
		for(Element elem : selection)
			shallowScrapeResult.add(target.retrieveTarget(elem));
		
		return shallowScrapeResult;
	}
	
}
