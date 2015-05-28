package org.fscraper.scraper.extractor;/**
 * Created by root on 28/05/15.
 */

import org.fscraper.scraper.DataExtractor;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Jamil Rzayev
 */


@Service("FCDataExtractor")
public class FCDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(FCDataExtractorImpl.class.getName());

    private static final String BRAND = "French Connection";

    public FCDataExtractorImpl(){

    }

    @Override
    protected String getTitle() {

        return document.select("h1#product_title").text();
    }

    @Override
    protected String getDescription() {
        return document.select("div#product_intro").text();
    }

    @Override
    protected String getBrand() {
        return BRAND;
    }

    @Override
    protected String getSKU() {

        Element pEl = document.select("p:contains(style code)").first();

        String stylecode = pEl.text();
        String[] parts = stylecode.split("-");
        String skuCode = parts[1].trim();

        return skuCode;
    }

    @Override
    protected Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements sizeEls = document.select("slect#selSize > option");

        if (sizeEls == null)
            return sizeSet;

        String sizeValue;

        for (Element sizeEl : sizeEls) {

            sizeValue = sizeEl.text();

            sizeSet.add(sizeValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        Element el = document.select("select#selColour").first();

        if (el == null )
            return colorSet;

        Elements colorEls = el.select("option[value]");

        String color;
        for (Element colorEl : colorEls) {
            color = colorEl.attr("value");
            colorSet.add(color);
        }

        return colorSet;
    }

    @Override
    protected Set<String> getImages() {

        Set<String> imgURLSet = new HashSet<>();

        Elements aEls = document.select("div.viewport > a");

        for (Element aEl : aEls) {

            String relAttr = aEl.attr("rel");

            Integer index = relAttr.indexOf("largeimage:");

            String imgURL = relAttr.substring(index +1, relAttr.length());

            imgURLSet.add(imgURL);

        }

        return imgURLSet;
    }

    @Override
    protected String getPrice() {
        return document.select("span#product_price").text();
    }
}
