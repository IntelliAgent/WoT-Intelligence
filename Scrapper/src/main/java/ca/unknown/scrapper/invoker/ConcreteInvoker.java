package ca.unknown.scrapper.invoker;

import ca.unknown.scrapper.action.Action;

public class ConcreteInvoker implements Invoker{

	Action firstAction;
	
	public ConcreteInvoker(){
		
	}
	
	public ConcreteInvoker(Action firstAction) {
		setFirstAction(firstAction);
	}

	@Override
	public void start() {
		firstAction.execute();
	}

	@Override
	public void setFirstAction(Action firstAction) {
		this.firstAction = firstAction;
	}

}
