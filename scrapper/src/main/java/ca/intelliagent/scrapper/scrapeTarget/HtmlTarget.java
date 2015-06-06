package ca.intelliagent.scrapper.scrapeTarget;

import org.jsoup.nodes.Element;

/**
 * @author TheCoconutChef
 *         <p/>
 *         Retrieves inner content of a member of the selection
 */
public class HtmlTarget extends AbstractTarget {

    public HtmlTarget(String selectString) {
        super(selectString);
    }

    @Override
    public String retrieveTarget(Element elem) {
        return elem.html();
    }
}
