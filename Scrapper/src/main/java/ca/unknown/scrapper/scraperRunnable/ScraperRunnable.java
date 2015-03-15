package ca.unknown.scrapper.scraperRunnable;

import java.util.Collection;

public interface ScraperRunnable extends Runnable{
	public void setScrapeResult(Collection<String> scraps);
}
