package ca.unknown.scrapper;

import ca.unknown.scrapper.action.Action;
import ca.unknown.scrapper.action.ExitAction;
import ca.unknown.scrapper.action.FollowLinkAction;
import ca.unknown.scrapper.action.ScrapeAction;
import ca.unknown.scrapper.invoker.ConcreteInvoker;
import ca.unknown.scrapper.invoker.Invoker;
import ca.unknown.scrapper.scrapeTarget.AttributeTarget;
import org.junit.Test;

public class JsoupHtmlScrapperTest {
    String entryPoint = "http://www.vbaddict.net/battlehistory.php?go=search";

    String replayPageLinkSelection = "#table-statistics tr td:last-child a";

    @Test
    public void test() {
        HtmlScrapper scrapper = new JsoupHtmlScrapper(entryPoint);

        Action scrape = new ScrapeAction(scrapper, new AttributeTarget(replayPageLinkSelection, "href"));
        Action followLink = new FollowLinkAction(scrapper, new AttributeTarget("a[rel=next]", "href"));
        Action exit = new ExitAction(scrapper);

        scrape.setCallback(followLink);
        followLink.setCallback(scrape);
        followLink.setFailureCallback(exit);

        Invoker invoker = new ConcreteInvoker(scrape);

        invoker.setMaxExecution(2); //Fetching for 5 pages
        invoker.run();

        System.out.println("Number of scrap element " + scrapper.getScrapeResult().size());

        for (String scrapeResult : scrapper.getScrapeResult())
            System.out.println(scrapeResult);
    }

}
