package org.fscraper.scraper.impl;/**
 * Created by root on 28/05/15.
 */

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Jamil Rzayev
 */

@Service("FC")
public class FCScraperServiceImpl
        extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(FCScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "FCDataExtractor";

    public FCScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return FCScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        if (document == null || !document.hasText())
            return urlSet;

        Elements products = document.select("div.category_product");

        if (products.isEmpty())
            return urlSet;

        String url;

        for (Element productRowEl: products) {

            Elements aEl = productRowEl.select("a");

            url = aEl.attr("abs:href");

            if (!urlSet.contains(url))
                urlSet.add(url);

            if (count > 0 && urlSet.size() == count)
                break;

        }

        return urlSet;
    }

}
