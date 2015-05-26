package org.fscraper.scraper.impl;


import org.fscraper.scraper.ScraperService;
import org.fscraper.scraper.AbstractScraperService;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("MS")
public class MSScraperServiceImpl extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(MSScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "MSDataExtractor";

    public MSScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return MSScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        Elements elements =  document.select("a.prodAnchor");

        Iterator<Element> iterator = elements.iterator();

        while (iterator.hasNext()) {

            String url = iterator.next().attr("abs:href");

            if (!urlSet.contains(url))
                urlSet.add(url);

            if ( count > 0 && urlSet.size() == count)
                break;
        }

        return urlSet;
    }
}
