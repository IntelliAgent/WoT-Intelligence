package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

import java.util.Map;

public class PostRequestAction extends AbstractAction {

    Map<String, String> postParam;

    public PostRequestAction(HtmlScrapper scrapper, Map<String, String> postParam) {
        super(scrapper);
        this.postParam = postParam;
    }
    
    public PostRequestAction(HtmlScrapper scrapper, Map<String, String> postParam, String url){
      super(scrapper);
      this.postParam = postParam;
    }
}
