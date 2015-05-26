package org.fscraper.entry;

import org.apache.commons.cli.Options;

/**
 * Created by jr on 3/22/2015.
 */
public class OptionsBuilder {

    public static Options build() {

        Options options = new Options();

        options.addOption("s", "store", true, "Store");
        options.addOption("c", "category", true, "Categories");
        options.addOption("q", "quantity", true, "Quantity");
        options.addOption("m", "mode", true, "Model");
        options.addOption("d", "data", true, "Data Source");

        return options;
    }
}
