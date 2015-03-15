package ca.unknown.scrapper.action;

/**
 * 
 * @author TheCoconutChef
 *
 *	Defines actions to be performed by the scrapper
 *
 */
public interface Action {
	/**
	 * 
	 */
	public Action execute();
	/**
	 * 
	 */
	public void setCallback(Action callback);
	/**
	 * 
	 */
	public void setFailureCallback(Action callback);
}
