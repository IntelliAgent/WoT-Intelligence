package ca.intelliagent.scrapper.action;

import ca.intelliagent.scrapper.HtmlScrapper;
import ca.intelliagent.scrapper.scrapeTarget.ScrapeTarget;

import java.util.Collection;

public class ScrapeAction extends AbstractAction {
	
	ScrapeTarget target;

	Collection<String> memory;
	
	public ScrapeAction(HtmlScrapper scrapper, ScrapeTarget target, Collection<String> memory) {
		super(scrapper);
		this.target = target;
		this.memory = memory;
	}

	@Override
	public Action execute(){
		scraper.scrape(target);
		
		memory.addAll(scraper.getScrapeResult());
		
		return successCallback;
	}
}
