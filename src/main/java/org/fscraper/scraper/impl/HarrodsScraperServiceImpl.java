package org.fscraper.scraper.impl;

import org.fscraper.scraper.AbstractScraperService;
import org.fscraper.scraper.ScraperService;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by jr on 3/22/2015.
 */

@Service("HR")
public class HarrodsScraperServiceImpl extends
        AbstractScraperService implements ScraperService {

    private static Logger logger = Logger.getLogger(HarrodsScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "HarrodsDataExtractor";

    public HarrodsScraperServiceImpl() {

    }

    @Override
    public String getDataExtractorName(){
        return HarrodsScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count){

        loadLandingPage();
        setScrapeSizes();

        Set<String> urlSet = new HashSet<>();

        if (document == null || !document.hasText())
            return urlSet;

        Elements products = document.select("ul.products_row");

        if (products.isEmpty())
            return urlSet;

        String url;

        for (Element productRowEl: products) {

            Elements liEls = productRowEl.select("li");

            Iterator<Element> iterator = liEls.iterator();

            while (iterator.hasNext()) {

                Element li = iterator.next();

                if (li.attr("class").contains("add_container"))
                    continue;

                Element imgEl = li.select("a").first();

                url = imgEl.attr("abs:href");

                if (!urlSet.contains(url))
                    urlSet.add(url);

                if (count > 0 && urlSet.size() == count)
                    break;
              }
        }

        return urlSet;
    }
}
