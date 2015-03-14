package ca.unknown.scrapper.action;

import java.util.Map;

import ca.unknown.scrapper.HtmlScrapper;

public class PostRequestAction extends AbstractAction{

	Map<String,String> postParam;
	
	public PostRequestAction(HtmlScrapper scrapper){
		super(scrapper);
	}
}
