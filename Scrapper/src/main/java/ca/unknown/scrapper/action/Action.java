package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

/**
 * 
 * @author TheCoconutChef
 *
 *	Defines actions the scrapper must perform during / before 
 *	or after a given target is being scrapped
 *
 */
public interface Action {
	/**
	 * 
	 */
	public void execute();
	/**
	 * 
	 */
	public void setCallback(Action callback);
	/**
	 * 
	 */
	public void setFailureCallback(Action callback);
}
