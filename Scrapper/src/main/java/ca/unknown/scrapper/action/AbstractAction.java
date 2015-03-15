package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

public class AbstractAction implements Action {

    protected HtmlScrapper scraper;

    protected Action successCallback;

    protected Action failureCallback;

    public AbstractAction(HtmlScrapper scrapper) {
        this.scraper = scrapper;
    }

    @Override
    public Action execute() {
        return new ExitAction(scraper);
    }

    @Override
    public void setCallback(Action callback) {
        this.successCallback = callback;
    }

    @Override
    public void setFailureCallback(Action callback) {
        this.failureCallback = callback;
    }

}
