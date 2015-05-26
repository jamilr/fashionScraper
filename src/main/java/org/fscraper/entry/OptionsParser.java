package org.fscraper.entry;

import org.apache.commons.cli.*;

/**
 * Created by jr on 3/22/2015.
 */

public class OptionsParser {

    public static ScraperOptions parse(Options options, String[] args) {

        ScraperOptions scraperOptions = new ScraperOptions();

        CommandLineParser parser = new BasicParser();
        CommandLine cmd;

        try{
            cmd = parser.parse(options, args);
        } catch (ParseException pe) {
            usage(options);
            return scraperOptions;
        }

        String mode =       getOptionValue(cmd, ScraperOptions.MODE);
        String storeName =  getOptionValue(cmd, ScraperOptions.STORE);
        String categories = getOptionValue(cmd, ScraperOptions.CATEGORY);
        String quantity =   getOptionValue(cmd, ScraperOptions.QUANTITY);
        String dataSource = getOptionValue(cmd, ScraperOptions.DATASOURCE);

        storeName = storeName.trim().toLowerCase();

        if (categories.equals("all")) {

            scraperOptions.setAllCategories(Boolean.TRUE);
        } else {

            String[] categoryList = categories.trim().toLowerCase().split(",");
            CategoryParam[] categoryParams = geCategoryParams(categoryList);
            scraperOptions.setCategories(categoryParams);

            scraperOptions.setCategories(categoryParams);
        }

        ScraperMode scraperMode = Enum.valueOf(ScraperMode.class, mode.toUpperCase());

        Integer quantityValue = (!quantity.equals(ScraperOptions.NO_VALUE)) ?  Integer.parseInt(quantity.trim()) : -1;

        DataSourceType dataSourceType = DataSourceType.find(dataSource);

        scraperOptions.setStore(storeName);
        scraperOptions.setMode(scraperMode);
        scraperOptions.setQuantity(quantityValue);
        scraperOptions.setDataSourceType(dataSourceType);

        return scraperOptions;
    }

    private static CategoryParam[] geCategoryParams(String[] categoryList){

        CategoryParam[] categoryParams = new CategoryParam[categoryList.length];

        int i = 0;
        for (String ctg: categoryList) {

            String[] categoryGender = ctg.split("-");

            CategoryParam categoryParam = new CategoryParam();

            categoryParam.setCategory(categoryGender[0]);
            categoryParam.setGender(categoryGender[1]);

            categoryParams[i] = categoryParam;

            i++;
        }

        return categoryParams;

    }

    public static String getOptionValue(CommandLine cmd, String optionName) {

        String value = ScraperOptions.NO_VALUE;
        if(cmd.hasOption(optionName))
            value = cmd.getOptionValue(optionName);

        return value;
    }

    private static void usage(Options options) {

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "CLIDemo", options );
    }
}
