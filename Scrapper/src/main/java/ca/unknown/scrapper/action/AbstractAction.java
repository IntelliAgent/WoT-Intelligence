package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

public class AbstractAction implements Action{

	protected HtmlScrapper scrapper;
	
	protected Action successCallback;
	
	protected Action failureCallback;
	
	public AbstractAction(HtmlScrapper scrapper){
		this.scrapper = scrapper;
	}
	
	@Override
	public Action execute() {
		return new ExitAction(scrapper);
	}

	@Override
	public void setCallback(Action callback) {
		this.successCallback = callback;
	}

	@Override
	public void setFailureCallback(Action callback) {
		this.failureCallback = callback;
	}

}
