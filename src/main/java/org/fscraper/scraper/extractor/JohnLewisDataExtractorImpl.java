package org.fscraper.scraper.extractor;

import org.fscraper.scraper.DataExtractor;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jr on 3/25/2015.
 */

@Service("JohnLewisDataExtractor")
public class JohnLewisDataExtractorImpl extends AbstractDataExtractor implements DataExtractor {

    private static Logger logger = Logger.getLogger(JohnLewisDataExtractorImpl.class.getName());

    public JohnLewisDataExtractorImpl(){

    }

    @Override
    protected String getTitle() {

        String titleString = document.select("span[itemprop=name]").text();
        String brandString = getBrand();
        titleString = titleString.replaceAll(brandString, "");

        return titleString.split(",")[0];
    }

    @Override
    protected String getDescription() {

        return this.document.select("span[itemprop=description]").text();
    }

    @Override
    protected String getBrand() {

        String brand = document.select("div[itemprop=brand] span").text();
        return (brand != null) ?  brand: "";
    }

    @Override
    protected String getSKU() {

        return this.document.select("div#prod-product-code p").text();
    }

    @Override
    protected Set<String> getImages() {

        Set<String> imgURLList = new HashSet<>();

        Elements imgEls = document.select("ul.wrapper img");

        for (Element imgEl: imgEls) {

            String url = imgEl.attr("abs:src");

            if (imgURLList.contains(url))
                continue;

            imgURLList.add(url);
        }

        return imgURLList;
    }

    @Override
    protected Set<String> getSizes() {

        Elements sizeEls = document.select("span.size");

        Set<String> sizes = new HashSet<>();

        for (Element sizeEl: sizeEls) {
            String sizeValue = sizeEl.text();
            sizes.add(sizeValue);
        }

        return sizes;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorList = new HashSet<>();

        String colorValue = document.select("h1#prod-title").text();

        if (!colorValue.contains(",")) {
            return colorList;
        }

        String colorPart = colorValue.split(",")[1];

        if (colorPart.contains("/")) {
            String[] colors = colorPart.split("/");
            for (String c: colors) {
                colorList.add(c);
            }
        } else
            colorList.add(colorPart);

        return colorList;
    }

    @Override
    protected String getPrice() {

        String priceValue = this.document.select("span.now-price").text();
        return super.parsePrice(priceValue);
    }

}
