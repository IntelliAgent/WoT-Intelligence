package ca.unknown.scrapper.scrapeTarget;

public abstract class AbstractTarget implements ScrapeTarget{
	/**
	 * 
	 */
	private String selectString;
	/**
	 * 
	 * @param selection
	 */
	public AbstractTarget(String selection){
		selectString = selection;
	}
	
	@Override
	public String getSelectString(){
		return selectString;
	}
}
