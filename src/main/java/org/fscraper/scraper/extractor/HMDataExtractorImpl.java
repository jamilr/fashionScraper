package org.fscraper.scraper.extractor;/**
 * Created by root on 28/05/15.
 */

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.logging.Logger;

/**
 * @author Jamil Rzayev
 */

@Service("HMDataExtractor")
public class HMDataExtractorImpl extends AbstractDataExtractor  {

    private static Logger logger = Logger.getLogger(HMDataExtractorImpl.class.getName());

    public HMDataExtractorImpl(){

    }

    @Override
    protected String getTitle() {
        return null;
    }

    @Override
    protected String getDescription() {
        return null;
    }

    @Override
    protected String getBrand() {
        return null;
    }

    @Override
    protected String getSKU() {
        return null;
    }

    @Override
    protected Set<String> getSizes() {
        return null;
    }

    @Override
    protected Set<String> getColors() {
        return null;
    }

    @Override
    protected Set<String> getImages() {
        return null;
    }

    @Override
    protected String getPrice() {
        return null;
    }
}
