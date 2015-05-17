package ca.unknown.scrapper;

import ca.unknown.scrapper.action.*;
import ca.unknown.scrapper.invoker.ConcreteInvoker;
import ca.unknown.scrapper.invoker.Invoker;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsoupHtmlScrapperTest {
	
	@Test
	public void shouldPrintABunchOfRelativeLinkComingFromTheVbAddictBattleHistoryTable() {
		String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";
		
		String replayPageLinkSelection = "#table-statistics tr td:last-child a";
		
		HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
		Collection<String> scraps = new ArrayList<String>();
		List<Map<String, List<String>>> redirects = new ArrayList<Map<String, List<String>>>();
		
		Action scrape		= new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection,"href"), scraps);
		Action getHeader	= new GetRedirectResponseAction(scrapper, new AttributeTarget(replayPageLinkSelection, "href"), redirects);
		Action followLink 	= new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]","href"));
		Action exit 		= new ExitAction(scrapper);
				
		scrape.setCallback(getHeader);
		getHeader.setCallback(followLink);
		followLink.setCallback(scrape);
		followLink.setFailureCallback(exit);
		
		Invoker invoker 	= new ConcreteInvoker(scrape);
		
		invoker.setMaxExecution(2); //Fetching for 2 pages
		invoker.run();
		
		System.out.println("Number of scrap element " + scrapper.getScrapeResult().size());
		
		for(String scrapeResult : scraps)
			System.out.println(scrapeResult);
		
		for(Map<String, List<String>> header : redirects){
			for(Map.Entry<String, List<String>> entry : header.entrySet()){
				System.out.print(entry.getKey() + " : ");
				for(String values : entry.getValue())
					System.out.println(values);
			}
		}
	}
	
	@Test
	public void shouldStartThreeAsynchronousExecutionForScrapeResult(){
		Thread t = new Thread();
		
		String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";
		
		String replayPageLinkSelection = "#table-statistics tr td:last-child a";
		
		HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
		Collection<String> scraps = new ArrayList<String>();
		
		Action scrape		= new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection,"href"), scraps);

		Action followLink 	= new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]","href"));
		Action exit 		= new ExitAction(scrapper);
	}
}
