package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;

public class FollowLinkAction implements Action{

	String linkUrl;

	AttributeTarget attrTarget;
	
	public FollowLinkAction(String url){
		this.linkUrl = url;
	}
	
	public FollowLinkAction(AttributeTarget target){
		this.attrTarget = target;
	}
	
	@Override
	public void execute(HtmlScrapper htmlScrapper) {
		if(attrTarget != null){
			linkUrl = htmlScrapper.shallowScrape(attrTarget).get(0);
		}
		htmlScrapper.changePage(linkUrl);
	}

}
