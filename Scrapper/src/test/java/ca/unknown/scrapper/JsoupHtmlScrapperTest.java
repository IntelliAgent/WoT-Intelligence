package ca.unknown.scrapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.AsynchronousAction;
import ca.unknown.scrapper.action.ExitAction;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.action.ScrapeAction;
import ca.unknown.scrapper.invoker.ConcreteInvoker;
import ca.unknown.scrapper.invoker.Invoker;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;

public class JsoupHtmlScrapperTest {
	
	@Test
	public void shouldPrintABunchOfRelativeLinkComingFromTheVbAddictBattleHistoryTable() {
		String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";
		
		String replayPageLinkSelection = "#table-statistics tr td:last-child a";
		
		HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
		Collection<String> scraps = new ArrayList<String>();
		
		Action scrape		= new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection,"href"), scraps);
		Action followLink 	= new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]","href"));
		Action exit 		= new ExitAction(scrapper);
				
		scrape.setCallback(followLink);
		followLink.setCallback(scrape);
		followLink.setFailureCallback(exit);
		
		Invoker invoker 	= new ConcreteInvoker(scrape);
		
		invoker.setMaxExecution(2); //Fetching for 5 pages
		invoker.run();
		
		System.out.println("Number of scrap element " + scrapper.getScrapeResult().size());
		
		for(String scrapeResult : scraps)
			System.out.println(scrapeResult);
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
