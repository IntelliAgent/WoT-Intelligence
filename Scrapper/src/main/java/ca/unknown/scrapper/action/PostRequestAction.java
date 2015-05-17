package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

import java.io.IOException;
import java.util.Map;

public class PostRequestAction extends AbstractAction {

    private Map<String, String> postParam;

    private String url;
        
    public PostRequestAction(HtmlScrapper scrapper, Map<String, String> postParam, String url){
      super(scrapper);
      this.postParam = postParam;
    }
    
    @Override
    public Action execute(){
      try{
        scraper.postRequest(url, postParam);
      }catch(IOException e){
        return failureCallback;
      }
      return successCallback;
    }
}
