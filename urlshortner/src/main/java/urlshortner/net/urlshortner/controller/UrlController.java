package urlshortner.net.urlshortner.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import urlshortner.net.urlshortner.logic.GenerateShortUrl;
import urlshortner.net.urlshortner.modal.Analytics;
import urlshortner.net.urlshortner.modal.Url;
import urlshortner.net.urlshortner.repository.UrlRepository;
import urlshortner.net.urlshortner.service.UrlService;

import java.time.LocalDateTime;



@RestController
@RequestMapping("url/shortner")
@CrossOrigin(origins = "http://localhost:4200")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping("/{shortUrl}")
    public String redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletRequest request) {

        Url url = urlRepository.findByShorturl(shortUrl);

        if (url == null) {
            return "URL not found!";
        }

        // url expired
        if (url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDateTime.now())) {
            return "URL has expired!";
        }

        Analytics analytics = url.getAnalytics();
        analytics.incrementTotalVisits();
        String visitorHash = request.getHeader("User-Agent") + request.getRemoteAddr();
        analytics.addUniqueVisitor(visitorHash);

        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("mobile")) {
            analytics.incrementMobileVisits();
        } else {
            analytics.incrementDesktopVisits();
        }

        analytics.addVisitTimestamp(LocalDateTime.now());
        urlRepository.save(url);

        return "Redirecting to: " + url.getOriginalurl();
    }

    @PostMapping
    public Url generateShortUrl(@RequestBody UrlRequest urlRequest) {
        String originalUrl = urlRequest.getOriginalUrl();
        System.out.println("Original URL: " + originalUrl);

        if (!GenerateShortUrl.isUrlValid(originalUrl)) {
            System.out.println("URL is not valid");
            return null;
        }

        Url urlObject = new Url();
        urlObject.setOriginalurl(originalUrl);
        urlObject.setShorturl(GenerateShortUrl.getShortUrl(originalUrl));

        urlObject.setCreatedAt(LocalDateTime.now());

        urlObject.setExpirationDate(LocalDateTime.now().plusDays(7));

        return urlRepository.save(urlObject);
    }

    @GetMapping("/analytics/{shortUrl}")
    public String getUrlAnalytics(@PathVariable String shortUrl) {
        Url url = urlRepository.findByShorturl(shortUrl);
        if (url == null) {
            return "URL not found!";
        }

        if (url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDateTime.now())) {
            return "URL has expired!";
        }

        Analytics analytics = url.getAnalytics();

        return "Analytics for Short URL: " + url.getShorturl() + "\n\n\n" +
                "Original URL: " + url.getOriginalurl() + "\n" +
                "Total number of visits: " + analytics.getTotalVisits() + "\n" +
                "Unique Visitors: " + analytics.getUniqueVisitors().size() + "\n" +
                "Desktop Visits: " + analytics.getDesktopVisits() + "\n" +
                "Mobile Visits: " + analytics.getMobileVisits() + "\n" +
                "Time series data of visits : " + analytics.getVisitTimestamps();
    }
}

