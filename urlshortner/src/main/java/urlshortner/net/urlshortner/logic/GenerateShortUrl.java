package urlshortner.net.urlshortner.logic;

import com.google.common.hash.Hashing;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class GenerateShortUrl {

    public static String getShortUrl(String url) {

        String shortUrl = Hashing.murmur3_32_fixed().hashString(url, Charset.defaultCharset()).toString();
        return shortUrl;
    }


public static boolean isUrlValid(String url) {
    System.out.println("Validating URL: " + url);

    try {
        new URL(url);
        System.out.println("Is URL valid? true");
        return true;
    } catch (MalformedURLException e) {
        System.out.println("Is URL valid? false");
        return false;
    }
}

}

