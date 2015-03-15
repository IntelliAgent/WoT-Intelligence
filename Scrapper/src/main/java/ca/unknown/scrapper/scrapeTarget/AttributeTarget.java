package ca.unknown.scrapper.scrapeTarget;

import org.jsoup.nodes.Element;

/**
 * @author TheCoconutChef
 *         <p/>
 *         Retrieves the value of an attribute of the outter most tag of
 *         a member of a selection.
 */
public class AttributeTarget extends AbstractTarget {

    private String attribute;

    public AttributeTarget(String selectString, String attribute) {
        super(selectString);
        this.attribute = attribute;
    }

    @Override
    public String retrieveTarget(Element elem) {
        return elem.attr(attribute);
    }
}
