package org.fscraper.helpers;

/**
 * @author Jamil Rzayev March 2015
 */

public enum  ScraperParam {

    MS_DRESSES("http://www.marksandspencer.com/l/women/dresses", "dress"),
    MS_SKIRTS("http://www.marksandspencer.com/l/women/skirts", "skirt"),
    MS_COATS("http://www.marksandspencer.com/l/men/coats-and-casual-jackets", "coat"),
    MS_SHIRTS("http://www.marksandspencer.com/l/men/casual-shirts", "shirt"),
    MS_SHOES("http://www.marksandspencer.com/l/women/shoes-and-boots/all-shoes", "shoes"),
    MS_BAGS("http://www.marksandspencer.com/l/women/handbags", "bag"),

    ZARA_DRESSES("http://www.zara.com/uk/en/woman/dresses-c358003.html", "dress"),
    ZARA_SKIRTS("http://www.zara.com/uk/en/woman/skirts-c358006.html", "skirt"),
    ZARA_COATS("http://www.zara.com/uk/en/man/jackets-c586542.html", "coat"),
    ZARA_SHIRTS("http://www.zara.com/uk/en/man/shirts-c358053.html", "shirt"),
    ZARA_SHOES("http://www.zara.com/uk/en/woman/shoes-c358009.html", "shoes"),
    ZARA_BAGS("http://www.zara.com/uk/en/woman/handbags-c358019.html", "bag"),

    SF_DRESSES("http://www.selfridges.com/en/womens/clothing/dresses/" ,"dress"),
    SF_SKIRTS("http://www.selfridges.com/en/womens/clothing/skirts/", "skirt"),
    SF_SHIRTS("http://www.selfridges.com/en/mens/clothing/shirts/", "shirt"),
    SF_COATS("http://www.selfridges.com/en/mens/clothing/coats-jackets/", "coat"),
    SF_SHOES("http://www.selfridges.com/en/womens/shoes/heels/", "heels"),
    SF_BAGS("http://www.selfridges.com/en/womens/bags/tote-bags", "bag"),

    LV_SHOES("http://uk.louisvuitton.com/eng-gb/women/shoes/", "shoes"),
    LV_BAGS("http://uk.louisvuitton.com/eng-gb/women/handbags", "bags");

    protected String url;
    protected String category;

    ScraperParam(String URL, String category) {

        this.url = URL;
        this.category = category;
    }

    public String getURL(){
        return this.url;
    }

    public String getCategory(){
        return this.category;
    }

    @Override
    public String toString() {
        return this.url;
    }

}
