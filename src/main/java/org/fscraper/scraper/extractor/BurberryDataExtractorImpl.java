package org.fscraper.scraper.extractor;

import org.fscraper.scraper.DataExtractor;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/27/2015.
 */

@Service("BurberryDataExtractor")
public class BurberryDataExtractorImpl
        extends AbstractDataExtractor implements DataExtractor {

    private static Logger logger = Logger.getLogger(BurberryDataExtractorImpl.class.getName());

    private static final String BRAND = "Burberry";

    public BurberryDataExtractorImpl(){

    }

    @Override
    protected String getTitle() {

        return document.select("h1.product-title").text();
    }

    @Override
    protected String getDescription() {

        Element ulEl = document.select("li#description-panel ul").first();

        if (ulEl == null || !ulEl.hasText())
            return new String();

        return ulEl.text();
    }

    @Override
    protected String getBrand() {

        return BRAND;
    }

    @Override
    protected String getSKU() {

        return document.select("p.product-id span").text();
    }

    @Override
    protected Set<String> getSizes() {

        Elements sizeEls = document.select("li.size label");

        Set<String> sizeSet = new HashSet<>();

        if (sizeEls == null || sizeEls.isEmpty() || !sizeEls.hasText())
            return sizeSet;

        for (Element sizeEl: sizeEls) {

            String sizeStrVal = sizeEl.text();

            if (!sizeSet.contains(sizeStrVal))
                sizeSet.add(sizeStrVal);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        String colorStrValue = document.select("span.color-name").text();

        if (colorStrValue.trim().isEmpty())
            return colorSet;

        if (colorStrValue.contains("/")) {
            String[] colorStrValues = colorStrValue.split("/");
            for (String color : colorStrValues) {
                if (!colorSet.contains(color))
                    colorSet.add(color);
            }
        } else if (!colorSet.contains(colorStrValue))
            colorSet.add(colorStrValue);

        return colorSet;
    }

    @Override
    protected Set<String> getImages() {

        Elements imgEls = document.select("li.product-image");

        Set<String> imgURLSet = new HashSet<>();

        if (imgEls == null || imgEls.isEmpty())
            return imgURLSet;

        for (Element liEl: imgEls) {

            String imgURL = liEl.attr("abs:data-zoomed-src");

            imgURL = imgURL.substring(0,imgURL.lastIndexOf("?"));

            if (!imgURLSet.contains(imgURL))
                imgURLSet.add(imgURL);
        }

        return imgURLSet;
    }

    @Override
    protected String getPrice() {

        String priceValue = document.select("span.price-amount").first().text();

        return super.parsePrice(priceValue);
    }

}
