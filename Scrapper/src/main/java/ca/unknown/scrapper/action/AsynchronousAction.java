package ca.unknown.scrapper.action;

import ca.unknown.scrapper.HtmlScrapper;

public class AsynchronousAction<T> extends AbstractAction{

	Runnable runnable;
	
	public AsynchronousAction(HtmlScrapper scrapper, Runnable runnable) {
		super(scrapper);
		
		this.runnable = runnable;		
	}

	@Override
	public Action execute(){
		try{
			Thread t = new Thread(runnable);
			t.start();
		}catch(Exception e){
			e.printStackTrace();
			return super.execute();
		}
		return successCallback;
	}
}
