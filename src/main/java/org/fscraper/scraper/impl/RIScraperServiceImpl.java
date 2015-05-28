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

@Service("RIScraperService")
public class RIScraperServiceImpl  extends AbstractScraperService
        implements ScraperService {

    private static Logger logger = Logger.getLogger(RIScraperServiceImpl.class.getName());

    public static final String DATA_EXTRACTOR = "RIDataExtractor";

    public RIScraperServiceImpl(){

    }

    @Override
    public String getDataExtractorName(){
        return RIScraperServiceImpl.DATA_EXTRACTOR;
    }

    @Override
    protected Set<String> extractItemURL(Integer count) {

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
