package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;

public class FollowLinkAction extends AbstractAction {

    String linkUrl;

	AttributeTarget attrTarget;
	
	public FollowLinkAction(HtmlScrapper scrapper, String url){
		super(scrapper);
		this.linkUrl = url;
	}
	
	public FollowLinkAction(HtmlScrapper scrapper, AttributeTarget target){
		super(scrapper);
		this.attrTarget = target;
	}
	
	@Override
	public Action execute() {
		if(attrTarget != null){
			if(scrapper.scrape(attrTarget)){
				linkUrl = scrapper.getScrapeResult().get(0);
				
				try{
					scrapper.changePage(linkUrl);					
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
