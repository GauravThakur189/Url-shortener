package urlshortner.net.urlshortner.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlshortner.net.urlshortner.modal.Url;
import urlshortner.net.urlshortner.repository.UrlRepository;

import java.time.LocalDateTime;

import static urlshortner.net.urlshortner.logic.GenerateShortUrl.getShortUrl;
import static urlshortner.net.urlshortner.logic.GenerateShortUrl.isUrlValid;

//
//@Service
//public class UrlService {
//    @Autowired
//    private UrlRepository urlRepository;
//
//
//    public String getOriginlUrl(String id) {
//        return urlRepository.findByShortUrl(id);
//    }
//
//    public Url generateShortUrl(String url) {
//
//        // Log the original URL
//        System.out.println("Original URL: " + url);
//        if(! isUrlValid(url)) {
//            System.out.println("URL is not valid");
//            return null;
//        }
//        Url urlObject = new Url();
//        urlObject.setOriginalurl(url);
//        urlObject.setShorturl(getShortUrl(url));
//
//        return urlRepository.save(urlObject);
//    }
//}

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String getOriginlUrl(String shortUrl) {
        Url urlObject = urlRepository.findByShorturl(shortUrl);

        if (urlObject != null && urlObject.getExpirationDate() != null) {
            if (urlObject.getExpirationDate().isBefore(LocalDateTime.now())) {
                System.out.println("URL has expired");
                return null;
            }
        }
        return urlObject != null ? urlObject.getOriginalurl() : null;
    }

    public Url generateShortUrl(String url) {
        System.out.println("Original URL: " + url);

        if (!isUrlValid(url)) {
            System.out.println("URL is not valid");
            return null;
        }

        Url urlObject = new Url();
        urlObject.setOriginalurl(url);
        urlObject.setShorturl(getShortUrl(url));
        urlObject.setCreatedAt(LocalDateTime.now());
        urlObject.setExpirationDate(LocalDateTime.now().plusDays(7));
        return urlRepository.save(urlObject);
    }
}
