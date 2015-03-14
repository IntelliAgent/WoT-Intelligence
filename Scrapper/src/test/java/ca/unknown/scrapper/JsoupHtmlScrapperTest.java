package ca.unknown.scrapper;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.action.ScrapeAction;
import ca.unknown.scrapper.invoker.ConcreteInvoker;
import ca.unknown.scrapper.invoker.Invoker;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;

public class JsoupHtmlScrapperTest {

	String entryPoint = "http://drmcninja.com/archives/comic/29p01/";
	
	@Test
	public void test() {
		HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
		
		Action scrape		= new ScrapeAction(scrapper, new AttributeTarget("#comic>img","title"));
		Action followLink 	= new FollowLinkAction(scrapper, new AttributeTarget("a.next","href"));
		
		scrape.setCallback(followLink);
		followLink.setCallback(scrape);
		
		Invoker invoker 	= new ConcreteInvoker(scrape);
		
		invoker.start();
		
		for(String scrapeResult : scrapper.getScrapeResult())
			System.out.println(scrapeResult);
	}

}
