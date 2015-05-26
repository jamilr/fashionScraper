package org.fscraper.helpers;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * @author Jamil Rzayev March 2015
 */
public class ScraperHelper {

    private static Logger logger = LoggerFactory.getLogger(ScraperHelper.class.getSimpleName());

    private static final Integer    TIMEOUT = 40000;
    private static final String     AGENT = "Mozilla";

    public static Document loadHTMLDocument(String url) {

        Document document;
        try {

            Connection connection = Jsoup.connect(url);

            document = connection
                    .timeout(TIMEOUT)
                    .userAgent(AGENT)
                    .get();

        } catch(SocketTimeoutException socEx) {

            logger.error(socEx.getMessage(), socEx);
            document = null;
        } catch (HttpStatusException httpEx) {

            logger.error(httpEx.getMessage(), httpEx);
            document = null;
        }catch (IOException ex) {

            logger.error(ex.getMessage(), ex);
            document = null;
        } catch (Exception httEx) {

            logger.error(httEx.getMessage());
            document = null;
        }

        return document;
    }
}
