package ca.unknown.scrapper.invoker;

import ca.unknown.scrapper.action.Action;

public interface Invoker {
	public void start();
	
	public void setFirstAction(Action firstAction);
}
