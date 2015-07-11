package ca.intelliagent.scrapper;

import ca.intelliagent.scrapper.scrapeTarget.ScrapeTarget;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author TheCoconutChef
 *         <p>
 *         Scrapper is the main object trough which web scrapping is realized.
 *         <p>
 *         A scrapper may only interact with a web page in order to reach another web
 *         page of fetch a target, which is ultimatly html or an interfacing structure
 *         for html. Specifically, a scrapper may not download any content from a page.
 */
public interface HtmlScrapper {
    void changePage(String url);

    void postRequest(String url, Map<String, String> postParam) throws IOException;

    String getCurrentPageUrl();

    String getBaseUri();

    boolean scrape(ScrapeTarget target);

    List<String> getScrapeResult();

    void clearScrapeResult();
}
