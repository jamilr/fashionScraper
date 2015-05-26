package org.fscraper.scraper.impl;

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("SF")
public class SFScraperServiceImpl extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(SFScraperServiceImpl.class.getName());

    private static final String DATA_EXTRACTOR = "SelfridgesDataExtractor";

    public SFScraperServiceImpl(){

    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        if (document == null || !document.hasText())
            return urlSet;

        Elements elements =  document.select("div.productContainer a.description");

        Iterator<Element> iterator = elements.iterator();

        String url;
        while (iterator.hasNext()) {

            url = iterator.next().attr("abs:href");

            if (!urlSet.contains(url))
                urlSet.add(url);

            if (count > 0 && urlSet.size() == count)
                break;
        }

        return urlSet;
    }

    @Override
    public String getDataExtractorName(){
        return SFScraperServiceImpl.DATA_EXTRACTOR;
    }

}
