package ca.intelliagent.scrapper.action;

import ca.intelliagent.scrapper.HtmlScrapper;

import java.io.IOException;
import java.util.Map;

public class PostRequestAction extends AbstractAction {

    private final Map<String, String> postParam;

    private final String url;

    public PostRequestAction(HtmlScrapper scrapper, Map<String, String> postParam, String url) {
        super(scrapper);
        this.postParam = postParam;
        this.url = url;
    }

    @Override
    public Action execute() {
        try {
            scraper.postRequest(url, postParam);
        } catch (IOException e) {
            return failureCallback;
        }
        return successCallback;
    }
}
