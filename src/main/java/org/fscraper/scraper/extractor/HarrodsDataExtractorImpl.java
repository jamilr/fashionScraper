package org.fscraper.scraper.extractor;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Jamil Rzayev March 2015
 */

@Service("HarrodsDataExtractor")
public class HarrodsDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(HarrodsDataExtractorImpl.class.getName());

    public HarrodsDataExtractorImpl() {

    }

    @Override
    protected String getTitle() {

        return document.select("span.productname").text();
    }

    @Override
    protected String getDescription() {

        return document.select("p.description").html();
    }

    @Override
    protected String getPrice() {

        String priceValue = document.select("span[itemprop=price]").text();

        return super.parsePrice(priceValue);
    }

    @Override
    protected String getBrand() {

        return document.select("span.brand").text();
    }

    @Override
    protected String getSKU() {

        String productCode =  document.select("span.product_code").text();
        productCode = productCode.replaceAll("Product Code ", "");
        return productCode;
    }

    @Override
    protected Set<String> getImages() {

        Set<String> imgSet = new HashSet<>();

        Elements links = document.select("ul.alt_view a");

        for (Element aLink: links) {

            String url = aLink.attr("abs:href");

            if (!imgSet.contains(url))
                imgSet.add(url);
        }

        return imgSet;
    }

    @Override
    protected Set<String> getSizes() {

        Element ulEl = document.select("select#size").first();

        Set<String> sizeSet = new HashSet<>();

        Elements options = ulEl.select("option");

        if (options.isEmpty())
            return sizeSet;

        for (Element option: options){

            String value = option.html();

            if (!sizeSet.contains(value))
                sizeSet.add(value);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Set<String> colorSet = new HashSet<>();

        Elements liEls = document.select("dl#details li");
        if (liEls.isEmpty())
            return colorSet;

        int i = 0;
        int length = liEls.size();
        boolean found = false;

        while (i < length && !found ) {

            String color = liEls.get(i).html();

            if (color.contains("Colour: ")) {

                color = color.replaceAll("Colour: ", "");

                if (!colorSet.contains(color))
                    colorSet.add(color);;

                found = true;
            }

            i++;
        }

        return colorSet;
    }

}
