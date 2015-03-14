package ca.unknown.scrapper.invoker;

import ca.unknown.scrapper.action.Action;

public interface Invoker {
	public void run();
	
	public void setFirstAction(Action firstAction);
	
	public void setMaxExecution(long maxExecution);
}
