package ca.unknown.scrapper.scrapeTarget;

import org.jsoup.nodes.Element;

/**
 * 
 * @author TheCoconutChef
 *	
 *	Retrieves a whole member of a selection
 */
public class WholeTarget extends AbstractTarget{
	
	public WholeTarget(String selectString){
		super(selectString);
	}
	
	@Override
	public String retrieveTarget(Element elem) {
		return elem.toString();
	}
}
