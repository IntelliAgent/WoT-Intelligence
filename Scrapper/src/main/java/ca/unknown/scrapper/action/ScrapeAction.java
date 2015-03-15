package ca.unknown.scrapper.action;

import java.util.Collection;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

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
		scrapper.scrape(target);
		
		memory.addAll(scrapper.getScrapeResult());
		
		return successCallback;
	}
}
