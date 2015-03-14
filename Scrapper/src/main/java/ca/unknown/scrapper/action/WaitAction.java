package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

public class WaitAction extends AbstractAction{

	long ms;
	
	public WaitAction(HtmlScrapper scrapper, long ms){
		super(scrapper);
		this.ms = ms;
	}
	
	@Override
	public Action execute() {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return failureCallback;
		}
		return successCallback;
	}

}
