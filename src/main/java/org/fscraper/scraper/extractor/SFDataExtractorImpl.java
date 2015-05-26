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

@Service("SelfridgesDataExtractor")
public class SFDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(SFDataExtractorImpl.class.getName());

    public SFDataExtractorImpl(){

    }

    @Override
    public String getTitle() {

        return document.select("span.description").html();
    }

    @Override
    public String getPrice() {

        String priceValue = document.select("div.prices p").html();

        return super.parsePrice(priceValue);
    }

    @Override
    public String getBrand() {

        return document.select("span.title > a").html();
    }

    @Override
    public String getSKU() {

        return document.select("p.pcode span.val").html();
    }

    @Override
    public String getDescription() {

        return document.select("span.description").text();
    }

    @Override
    public Set<String> getImages() {

        Set<String> imgSet = new HashSet<>();

        Elements imgEls = document.select("img[itemprop=image]");

        if (imgEls.isEmpty())
            return imgSet;

        for (Element imgEl: imgEls) {

            String url = imgEl.attr("abs:src");

            if (url.isEmpty() || imgSet.contains(url))
                continue;

            imgSet.add(url);
        }

        return imgSet;
    }

    @Override
    public Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements sizeEls = document.select("fieldset[data-attribute=Size] label");

        if (sizeEls.isEmpty())
            return sizeSet;

        for (Element sizeEl: sizeEls) {
            String sizeValue = sizeEl.text().trim();
            if (!sizeValue.isEmpty())
                if (!sizeSet.contains(sizeValue))
                    sizeSet.add(sizeValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        Elements colorEls = document.select("input[name=Colour]");

        if (colorEls.isEmpty())
            return colorSet;

        for (Element colorEl: colorEls) {
            String colorValue = colorEl.attr("value").trim();
            if (!colorValue.isEmpty()) {
                if (colorValue.contains("/")) {
                    String[] colors = colorValue.split("/");
                    for (String color: colors) {

                        if (colorSet.contains(color))
                            continue;

                        colorSet.add(color);
                    }
                } else
                    colorSet.add(colorValue);
            }
        }

        return colorSet;
    }
}
