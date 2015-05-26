package org.fscraper.scraper.extractor;

import org.fscraper.scraper.DataExtractor;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/29/2015.
 */


@Service("PradaDataExtractor")
public class PradaDataExtractor extends AbstractDataExtractor implements DataExtractor {

    private static Logger logger = Logger.getLogger(PradaDataExtractor.class.getName());

    protected static String BRAND = "Prada";

    public PradaDataExtractor(){

    }

    @Override
    protected String getTitle() {

        return document.select("span.nameProduct").first().text();
    }

    @Override
    protected String getDescription() {

        return document.select("div.description").text();
    }

    @Override
    protected String getBrand() {

        return BRAND;
    }

    @Override
    protected String getSKU() {

        return document.select("div.title h1").first().text();
    }

    @Override
    protected Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements sizeEls = document.select("ul.size_list div");

        if (sizeEls == null || sizeEls.isEmpty())
            return sizeSet;

        for (Element sizeEl: sizeEls) {

            String sizeStrValue = sizeEl.text();

            if (sizeStrValue != null || !sizeStrValue.isEmpty() )
                if (!sizeSet.contains(sizeStrValue))
                    sizeSet.add(sizeStrValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        Element colorEl = document.select("div.color span").first();

        String colorStrValue = new String();

        if (colorEl != null)
            colorStrValue = colorEl.text();

        if (colorStrValue != null && !colorStrValue.isEmpty())
            colorSet.add(colorStrValue);

        return colorSet;
    }

    @Override
    protected Set<String> getImages() {

        Elements imgEls = document.select("div.gallery img");

        Set<String> imgURLSet = new HashSet<>();

        for (Element imgEl: imgEls){

            String imgURL = imgEl.attr("abs:src");

            if (!imgURLSet.contains(imgURL))
                imgURLSet.add(imgURL);
        }

        return imgURLSet;

    }

    @Override
    protected String getPrice() {

        String priceValue = document.select("div.price span").first().text().replaceAll(" ", "");
        return super.parsePrice(priceValue);
    }
}
