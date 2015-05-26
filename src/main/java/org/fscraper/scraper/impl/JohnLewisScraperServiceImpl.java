package org.fscraper.scraper.impl;

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jr on 3/25/2015.
 */

@Service("JL")
public class JohnLewisScraperServiceImpl extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(JohnLewisScraperServiceImpl.class.getName());

    private static final String DATA_EXTRACTOR = "JohnLewisDataExtractor";

    public JohnLewisScraperServiceImpl() {

    }

    @Override
    public String getDataExtractorName(){
        return JohnLewisScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    public Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        Elements products = document.select("a.product-link");

        if (products.isEmpty())
            return urlSet;

        Iterator<Element> iterator = products.iterator();

        while (iterator.hasNext()){

            String url = iterator.next().attr("abs:href");

            if (!urlSet.contains(url))
                urlSet.add(url);

            if ( count > 0 && urlSet.size() == count)
                break;
        }

        return urlSet;
    }
}
