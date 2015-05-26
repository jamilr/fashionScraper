package org.fscraper.scraper.extractor;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * @author Jamil Rzayev March 2015
 */

@Service("MSDataExtractor")
public class MSDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(MSDataExtractorImpl.class.getName());

    public MSDataExtractorImpl(){

    }

    @Override
    public String getTitle() {

        return document.select("div.information h1.body2").text();
    }

    @Override
    public String getPrice() {

        String priceValue = document.select("span[data-mapping=price]").attr("data-value");

        return super.parsePrice(priceValue);
    }

    @Override
    public String getBrand() {

        Element el = document.select("div.information li.sb-logo").first();
        return (el == null) ? "" : document.select("div.information li.sb-logo").text();
    }

    @Override
    public String getSKU() {

        Element el = document.select("div.information p.code").first();
        return (el == null) ? "" : document.select("div.information p.code").text();
    }

    @Override
    public String getDescription() {

        return document.select("div.product-information-flyout p").text();
    }

    @Override
    public Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements sizeEls = document.select("table.sizes label");

        for (Element sizeEl: sizeEls) {

            String sizeStrValue = sizeEl.text();

            if (!sizeSet.contains(sizeStrValue))
                sizeSet.add(sizeStrValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Elements colorEls = document.select("input[name=colour]");

        Set<String> colorSet = new HashSet<>();

        for (Element colorEl: colorEls) {

            String colorStrValue = colorEl.attr("value");

            if (!colorSet.contains(colorStrValue))
                colorSet.add(colorStrValue);
        }

        return colorSet;
    }

    public Set<String> getImages() {

        Set<String> imgSet = new HashSet<>();

        Elements els = document.select("div.caro-wrap img.btn");

        for (int i = 0; i < els.size(); i++) {

            Element el = els.get(i);

            String imgURL = el.attr("abs:src");

            if (imgURL.isEmpty()) {
                imgURL = el.attr("srcset").split(",")[0].split(" ")[0];
                imgURL = imgURL.replaceAll("//", "");
            }

            if (!imgSet.contains(imgURL))
                imgSet.add(imgURL);
        }

        return imgSet;
    }
}
