package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

public class ShallowScrapeAction extends AbstractAction {

	ScrapeTarget target;
	
	public ShallowScrapeAction(HtmlScrapper scrapper, ScrapeTarget target) {
		super(scrapper);
		this.target = target;
	}
	
	@Override
	public Action execute(){
		scrapper.shallowScrape(target);
		
		return successCallback;
	}

}
