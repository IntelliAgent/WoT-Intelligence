package ca.intelliagent.scrapper;

import ca.intelliagent.scrapper.action.*;
import ca.intelliagent.scrapper.invoker.ConcreteInvoker;
import ca.intelliagent.scrapper.invoker.Invoker;
import ca.intelliagent.scrapper.scrapeTarget.AttributeTarget;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

public class JsoupHtmlScrapperTest {

  @Test
  public void shouldPrintABunchOfRelativeLinkComingFromTheVbAddictBattleHistoryTable() {
    String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";

    String replayPageLinkSelection = "#table-statistics tr td:last-child a";

    HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
    Collection<String> scraps = new ArrayList<String>();
    List<Map<String, List<String>>> redirects = new ArrayList<Map<String, List<String>>>();

    Action scrape = new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection, "href"), scraps);
    Action getHeader = new GetRedirectResponseAction(scrapper, new AttributeTarget(replayPageLinkSelection, "href"), redirects);
    Action followLink = new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]", "href"));
    Action exit = new ExitAction(scrapper);

    scrape.setCallback(getHeader);
    getHeader.setCallback(followLink);
    followLink.setCallback(scrape);
    followLink.setFailureCallback(exit);

    Invoker invoker = new ConcreteInvoker(scrape);

    invoker.setMaxExecution(2); // Fetching for 2 pages
    invoker.run();

    System.out.println("Number of scrap element " + scrapper.getScrapeResult().size());

    for (String scrapeResult : scraps)
      System.out.println(scrapeResult);

    for (Map<String, List<String>> header : redirects) {
      for (Entry<String, List<String>> entry : header.entrySet()) {
        System.out.print(entry.getKey() + " : ");
        for (String values : entry.getValue())
          System.out.println(values);
      }
    }
  }

  @Test
  public void onlyTakeBattleLinkThatHaveReplays() {
    String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";

    String replayPageLinkSelection = "#table-statistics tr td:last-child a";

    HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);
    Collection<String> scraps = new ArrayList<String>();

    Map<String, String> postParam = new HashMap<String, String>();

    postParam.put("age", "0");
    postParam.put("uploader", "");
    postParam.put("playername", "");
    postParam.put("server", "eu");
    postParam.put("nation", "0");
    postParam.put("tier", "0");
    postParam.put("type", "0");
    postParam.put("premium", "0");
    postParam.put("mapid", "0");
    postParam.put("modeid", "0");
    postParam.put("replays", "1");

    Action post = new PostRequestAction(scrapper, postParam, entryPoint);
    Action scrape = new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection, "href"), scraps);
    Action followLink = new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]", "href"));
    Action exit = new ExitAction(scrapper);

    post.setCallback(scrape);
    post.setFailureCallback(exit);
    scrape.setCallback(followLink);
    followLink.setCallback(scrape);
    followLink.setFailureCallback(exit);

    Invoker invoker = new ConcreteInvoker(post);

    invoker.setMaxExecution(2); // Fetching for 2 pages
    invoker.run();

    System.out.println("Number of scrap element for post " + scrapper.getScrapeResult().size());
    for (String scrapeResult : scraps)
      System.out.println(scrapeResult);
  }
}
