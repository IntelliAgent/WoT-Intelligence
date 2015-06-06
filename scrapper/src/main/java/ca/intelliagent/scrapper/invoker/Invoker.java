package ca.intelliagent.scrapper.invoker;

import ca.intelliagent.scrapper.action.Action;

public interface Invoker {
    void run();

    void setFirstAction(Action firstAction);

    void setMaxExecution(long maxExecution);
}
