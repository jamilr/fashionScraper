package org.fscraper.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Jamil Rzayev 2015 March
 */

public class HttpHelpers {

    public static String httpGet(String urlStr) throws IOException {

        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200)
            throw new IOException(conn.getResponseMessage());

        BufferedReader rd = new BufferedReader( new InputStreamReader(conn.getInputStream()));

        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = rd.readLine()) != null)
            sb.append(line);

        rd.close();

        conn.disconnect();

        return sb.toString();
    }
}
