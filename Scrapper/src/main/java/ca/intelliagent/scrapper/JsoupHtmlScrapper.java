package ca.intelliagent.scrapper;

import ca.intelliagent.scrapper.scrapeTarget.ScrapeTarget;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Jsoup based implementation of scrapper interface
 */
public class JsoupHtmlScrapper implements HtmlScrapper {

  private static final int TIMEOUT = 500000;
  private final List<String> scrapeResult = new ArrayList<String>();
  private URI currentPageUrl;
  private Document currentPage;


  /**
   * Empty JsoupScrapper constructor
   */
  public JsoupHtmlScrapper(String entryPoint) {
    changePage(entryPoint);
  }

  @Override
  public boolean scrape(ScrapeTarget target) {
    scrapeResult.clear();

    Elements selection = currentPage.select(target.getSelectString());

    for (Element elem : selection)
      scrapeResult.add(target.retrieveTarget(elem));

    return !scrapeResult.isEmpty();
  }

  @Override
  public void changePage(String url) {
    updateCurrentPageURL(url);

    try {
      currentPage = Jsoup.parse(currentPageUrl.toURL(), TIMEOUT);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<String> getScrapeResult() {
    return scrapeResult;
  }

  @Override
  public String getCurrentPageUrl() {
    return currentPageUrl.toString();
  }

  @Override
  public void clearScrapeResult() {
    scrapeResult.clear();
  }

  @Override
  public String getBaseUri() {
    return currentPage.baseUri();
  }

  @Override
  public void postRequest(String url, Map<String, String> postParam) throws IOException {
    updateCurrentPageURL(url);
    
    Connection postConnection = Jsoup.connect(currentPageUrl.toString());

    for (Entry<String, String> param : postParam.entrySet())
      postConnection.data(param.getKey(), param.getValue());
    
    postConnection.userAgent(UserAgent.MOZILLA_WINNT_61);

    try {
      currentPage = postConnection.post();
    } catch (IOException e) {
      throw e;
    }
  }

  /**
   * Builds an absolute URL based on the given url
   * @param url String from which to build absolute url
   */
  private void updateCurrentPageURL(String url) {
    try {
      currentPageUrl = new URI(url);
    } catch (URISyntaxException e1) {
      e1.printStackTrace();
    }

    if (!currentPageUrl.isAbsolute())
      try {
        currentPageUrl = new URI(currentPage.baseUri() + url);
      } catch (URISyntaxException e1) {
        e1.printStackTrace();
      }
  }
}
