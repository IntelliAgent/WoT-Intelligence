package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;

public class FollowLinkAction extends AbstractAction{

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
	public void execute() {
		if(attrTarget != null){
			linkUrl = scrapper.shallowScrape(attrTarget).get(0);
		}
		scrapper.changePage(linkUrl);
	}

}
