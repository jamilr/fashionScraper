package org.fscraper.entry;

import org.fscraper.config.SpringContextProvider;
import org.fscraper.scraper.ScraperGateway;
import org.apache.commons.cli.Options;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jamil Rzayev March 2015
 */

public class WebScraperEntry {

    private static ClassPathXmlApplicationContext context;

    private static Options options;

    public static void main(String[] args) {

        context = new ClassPathXmlApplicationContext( new String[]{
                "classpath:fscraper-services-context.xml",
        });

        SpringContextProvider.setContext(context);

        options = OptionsBuilder.build();

        ScraperOptions scraperOptions = OptionsParser.parse(options, args);

        ScraperGateway scraperGateway = (ScraperGateway)SpringContextProvider.getBean("ScraperGateway");

        scraperGateway.run(scraperOptions);
    }
}
