package ca.unknown.scrapper;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.target.AttributeTarget;

public class JsoupHtmlScrapperTest {

	String entryPoint = "http://drmcninja.com/archives/comic/29p01/";
	
	@Test
	public void test() {
		HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
		Action followLink = new FollowLinkAction(new AttributeTarget("a.next","href"));
		scrapper.addPostAction(followLink);
		scrapper.setTarget(new AttributeTarget("#comic>img","title"));
		
		for(int i = 0; i < 10; i++){
			scrapper.scrapeTarget();
		}
		
		for(String scrapeResult : scrapper.getScrapeTargetResult())
			System.out.println(scrapeResult);
	}

}
