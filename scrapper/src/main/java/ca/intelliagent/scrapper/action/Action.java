package ca.intelliagent.scrapper.action;

/**
 * @author TheCoconutChef
 *
 *	Defines actions to be performed by the scrapper
 *
 */
public interface Action {
    /**
     *
     */
    Action execute();

    /**
     *
     */
    void setCallback(Action callback);

    /**
     *
     */
    void setFailureCallback(Action callback);
}
