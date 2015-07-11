package ca.intelliagent.scrapper.scrapeTarget;

import org.jsoup.nodes.Element;

/**
 * @author TheCoconutChef
 *         <p>
 *         A target defines a type of element to fetch on a given page
 */
public interface ScrapeTarget {
    String getSelectString();

    String retrieveTarget(Element elem);
}
