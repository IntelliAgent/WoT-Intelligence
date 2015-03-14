package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.ScrapeTarget;

public class ScrapeAction extends AbstractAction {

	ScrapeTarget target;
	
	public ScrapeAction(HtmlScrapper scrapper, ScrapeTarget target) {
		super(scrapper);
		this.target = target;
	}

	@Override
	public void execute(){
		scrapper.scrape(target);
	}
}
