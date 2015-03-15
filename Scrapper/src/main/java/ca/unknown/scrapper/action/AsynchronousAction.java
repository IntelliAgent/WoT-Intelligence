package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;
import ca.unknown.scrapper.scraperRunnable.ScraperRunnable;

public class AsynchronousAction<T> extends AbstractAction{

	ScraperRunnable runnable;
	
	public AsynchronousAction(HtmlScrapper scrapper, ScraperRunnable runnable) {
		super(scrapper);
		
		this.runnable = runnable;		
	}

	@Override
	public Action execute(){
		runnable.setScrapeResult(scraper.getScrapeResult());
		Thread t = new Thread(runnable);
		try{
			t.start();
		}catch(Exception e){
			e.printStackTrace();
			return super.execute();
		}
		return successCallback;
	}
}
