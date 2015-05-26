package org.fscraper.scraper.extractor;

import org.fscraper.data.view.ProductView;
import org.fscraper.helpers.BrandParam;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Jamil Rzayev March 2015
 */

@Service("LVDataExtractor")
public class LVDataExtractorImpl extends AbstractDataExtractor {

    private static Logger logger = Logger.getLogger(LVDataExtractorImpl.class.getName());

    private static final String SIZE_ATTR_PREFIX = "Size/";

    public LVDataExtractorImpl(){

    }

    @Override
    public String getTitle() {

        return document.select("div#productName > h1").text();
    }

    @Override
    public String getPrice() {

        String priceValue = document.select("td.priceValue").text();
        return super.parsePrice(priceValue);
    }

    @Override
    public String getBrand() {

        return BrandParam.LV.toString();
    }

    @Override
    public String getSKU() {

        return document.select("h2.sku").text();
    }

    @Override
    public String getDescription() {

        return document.select("div.productDescription").text();
    }

    @Override
    public Set<String> getImages() {

        Set<String> imgURLSet = new LinkedHashSet<>();

        Elements liEls = document.select("ul.bigs li");
        Elements imgEls = liEls.select("img[src]");

        for (Element imgEl : imgEls) {

            String url = imgEl.attr("abs:src");

            url = url.replaceAll(" ", "%20");

            if (imgURLSet.contains(url))
                continue;

            imgURLSet.add(url);
        }

        return imgURLSet;
    }

    @Override
    public Set<String> getSizes() {

        Set<String> sizeSet = new HashSet<>();

        Elements optEls = this.document.select("div.sizesPanel li[data-ona]");

        for (Element el: optEls) {

            String sizeValue = el.attr("data-ona");

            sizeValue = sizeValue.replaceAll(SIZE_ATTR_PREFIX, "");

            if (sizeSet.contains(sizeValue))
                continue;

            sizeSet.add(sizeValue);
        }

        return sizeSet;
    }

    @Override
    protected Set<String> getColors() {

        Elements imgEls = this.document.select("ul.paletteContainer img");

        Set<String> colors = new HashSet<>();

        for (Element imgEl: imgEls) {

            String color = imgEl.attr("alt");

            if (colors.contains(color))
                continue;

            colors.add(color);
        }

        return colors;
    }

    @Override
    public ProductView extract(Document doc) {

        ProductView productView = super.extract(doc);

        if (this.toScrapeSizes) {
            productView.setSizes(getSizes());
        }

        Set<String> colorSet = getColors();

        if (colorSet.isEmpty())
            colorSet.add("other");

        productView.setColors(colorSet);

        return productView;
    }
}
