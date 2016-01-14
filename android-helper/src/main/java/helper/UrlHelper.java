package helper;

import android.net.Uri;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by MFQ on 2015-06-20.
 */
public class UrlHelper {

    /**
     * append the query string to the url
     * @param url your url
     * @param queryName : the query key
     * @param queryValue : the query value
     * @return return new url with the new query string
     */
    public static String buildQuery(String url, String queryName, String queryValue) {
        return Uri.parse(url).buildUpon().appendQueryParameter(queryName,
                queryValue).toString();
    }

    /**
     *  clear the a query string from a url having a query string
     * @param url
     * @return your url with no query
     */
    public static String clearUrlQuery(String url) {
        return Uri.parse(url).buildUpon().clearQuery().toString();
    }

    public static String decode(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "error";
    }

    public static String encode(String text) {
        try {
            return URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
