package ca.intelliagent.scrapper.scrapeTarget;

public abstract class AbstractTarget implements ScrapeTarget {
    /**
     *
     */
    private final String selectString;

    /**
     * @param selection
     */
    public AbstractTarget(String selection) {
        selectString = selection;
    }

    @Override
    public String getSelectString() {
        return selectString;
    }
}
