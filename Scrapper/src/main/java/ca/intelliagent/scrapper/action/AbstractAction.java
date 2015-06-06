package ca.intelliagent.scrapper.action;

import ca.intelliagent.scrapper.HtmlScrapper;

public abstract class AbstractAction implements Action {

    protected HtmlScrapper scraper;

    protected Action successCallback;

    protected Action failureCallback;

    public AbstractAction(HtmlScrapper scraper) {
        this.scraper = scraper;
    }

    @Override
    public Action execute() {
        return new ExitAction(scraper);
    }

    @Override
    public void setCallback(Action callback) {
        successCallback = callback;
    }

    @Override
    public void setFailureCallback(Action callback) {
        failureCallback = callback;
    }

}
