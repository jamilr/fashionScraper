package org.fscraper.scraper.impl;

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jr on 3/27/2015.
 */

@Service("BR")
public class BurberryScraperServiceImpl
        extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(BurberryScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "BurberryDataExtractor";

    public BurberryScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return BurberryScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        Elements els = document.select("a.product-link");

        Iterator<Element> iterator = els.iterator();

        while (iterator.hasNext()) {

            String url = iterator.next().attr("abs:href").trim();

            if (!urlSet.contains(url))
                urlSet.add(url);

            if (count > 0 && urlSet.size() == count)
                break;
        }

        return urlSet;
    }
}
