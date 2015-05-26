package org.fscraper.scraper.extractor;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("ZaraDataExtractor")
public class ZaraDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(ZaraDataExtractorImpl.class.getName());

    private static final String BRAND = "ZARA";

    public ZaraDataExtractorImpl(){

    }

    @Override
    public String getTitle() {
        return document.select("div.right h1").text();
    }

    @Override
    public String getPrice() {

        String priceValue = document.select("span.price").attr("data-price");

        if (priceValue == null || priceValue.isEmpty())
            return new Double(0.0).toString();

        return super.parsePrice(priceValue).substring(0, priceValue.length() -3).trim();
    }

    @Override
    public String getBrand() {
        return BRAND;
    }

    @Override
    public String getSKU() {
        return document.select("p.reference").text().replaceAll("Ref. ", "");
    }

    @Override
    public String getDescription() {
        return document.select("p.description").text();
    }

    @Override
    public Set<String> getImages() {

        Set<String> imgURLSet = new HashSet<>();

        Elements imgEls = document.select("div.bigImageContainer img");

        for (int i=0; i<imgEls.size(); i++) {

            Element imgEl = imgEls.get(i);

            if (!imgEl.hasAttr("data-src"))
                continue;

            String imgURL = imgEl.attr("abs:data-src");

            imgURLSet.add(imgURL);
        }

        return imgURLSet;
    }

    @Override
    public Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements sizeEls = document.select("td.size-name");

        if (sizeEls.isEmpty())
            return sizeSet;

        for (Element size: sizeEls){

            String sizeValue = size.text().trim();

            if (sizeValue == null || sizeValue.isEmpty())
                continue;

            sizeSet.add(sizeValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        Element colorEl = document.select("label.colorEl").first();

        if (colorEl == null)
            return colorSet;

        String colorValue = colorEl.text().trim();

        if (colorValue == null || colorValue.isEmpty())
            return colorSet;

        colorSet.add(colorValue);

        return colorSet;
    }
}
