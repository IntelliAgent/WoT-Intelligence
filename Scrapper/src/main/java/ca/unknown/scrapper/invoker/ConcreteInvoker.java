package ca.unknown.scrapper.invoker;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.ExitAction;

public class ConcreteInvoker implements Invoker {

    private Action firstAction;

    private Action currentAction;

    private long maxExecution = Long.MAX_VALUE;

    private long execution = 0;

    public ConcreteInvoker() {

    }

    public ConcreteInvoker(Action firstAction) {
        setFirstAction(firstAction);
    }

    @Override
    public void setMaxExecution(long maxExecution) {
        this.maxExecution = maxExecution;
    }

    @Override
    public void run() {
        currentAction = firstAction;

        while (!(currentAction instanceof ExitAction) && checkConditions()) {
            currentAction = currentAction.execute();
            execution++;
        }
    }

    @Override
    public void setFirstAction(Action firstAction) {
        this.firstAction = firstAction;
    }

    private boolean checkConditions() {
        return maxExecution == Long.MAX_VALUE ? true : execution <= maxExecution;
    }
}
