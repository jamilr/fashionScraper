package org.fscraper.scraper;

import org.fscraper.entry.ScraperMode;

/**
 * Created by jr on 3/26/2015.
 */
public class ScraperConfig {

    public static final Integer DEFAULT_QUANTITY = 20;

    private ScraperMode mode;

    private Integer quantity;

    public ScraperConfig(){
        this.mode = ScraperMode.LIMITED;
        this.quantity = DEFAULT_QUANTITY;
    }


    public ScraperMode getMode() {
        return mode;
    }

    public void setMode(ScraperMode mode) {
        this.mode = mode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
