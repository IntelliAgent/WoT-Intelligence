package ca.intelliagent.scrapper.action;

import ca.intelliagent.scrapper.HtmlScrapper;
import ca.intelliagent.scrapper.scrapeTarget.AttributeTarget;

public class FollowLinkAction extends AbstractAction {

    String linkUrl;

	AttributeTarget attrTarget;
	
	public FollowLinkAction(HtmlScrapper scrapper, String url){
		super(scrapper);
		linkUrl = url;
	}
	
	public FollowLinkAction(HtmlScrapper scrapper, AttributeTarget target){
		super(scrapper);
		attrTarget = target;
	}
	
	@Override
	public Action execute() {
		if(attrTarget != null){
			if(scraper.scrape(attrTarget)){
				linkUrl = scraper.getScrapeResult().get(0);
				
				try{
					scraper.changePage(linkUrl);					
				}catch(Exception e){
					return failureCallback;
				}
				
			}else{
				return failureCallback;
			}
		}
		return successCallback;
	}
}
