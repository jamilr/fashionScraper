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

@Service("ZR")
public class ZaraScraperServiceImpl extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(ZaraScraperServiceImpl.class.getName());

    private static final String DATA_EXTRACTOR = "ZaraDataExtractor";

    public ZaraScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return ZaraScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        Elements elements =  document.select("a.item");

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
}
