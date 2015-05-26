package org.fscraper.scraper.impl;

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jr on 3/21/2015.
 */

@Service("LV")
public class LVScraperServiceImpl extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(LVScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "LVDataExtractor";

    public LVScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return LVScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    public Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        if (document == null || !document.hasText())
            return urlSet;

        Elements products = document.select("a.product-item");

        if (products.isEmpty())
            return urlSet;

        Iterator<Element> iterator = products.iterator();

        String url;
        while (iterator.hasNext()){

            url = iterator.next().attr("abs:href");

            if (!urlSet.contains(url))
                urlSet.add(url);

            if ( count > 0 && urlSet.size() == count)
                break;
        }

        return urlSet;
    }
}
