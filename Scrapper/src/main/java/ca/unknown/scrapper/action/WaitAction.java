package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

public class WaitAction implements Action{

	long ms;
	
	public WaitAction(long ms){
		this.ms = ms;
	}
	
	@Override
	public void execute(HtmlScrapper htmlScrapper) {
		
	}

}
