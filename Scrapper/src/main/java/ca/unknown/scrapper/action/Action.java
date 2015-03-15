package ca.unknown.scrapper.action;

/**
 * @author TheCoconutChef
 *         <p/>
 *         Defines actions the scrapper must perform during / before
 *         or after a given target is being scrapped
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
