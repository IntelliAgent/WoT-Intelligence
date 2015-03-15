package ca.unknown.scrapper.scrapeTarget;

import org.jsoup.nodes.Element;

/**
 * @author TheCoconutChef
 *         <p/>
 *         A target defines a type of element to fetch on a given page
 */
public interface ScrapeTarget {
    public String getSelectString();

    public String retrieveTarget(Element elem);
}
