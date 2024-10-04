package urlshortner.net.urlshortner.cleanup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import urlshortner.net.urlshortner.repository.UrlRepository;

import java.time.LocalDateTime;

@Component
public class ExpiredUrlCleanupTask {

    @Autowired
    private UrlRepository urlRepository;


    @Scheduled(cron = "0 0 0 * * *")
    public void deleteExpiredUrls() {
        LocalDateTime now = LocalDateTime.now();
        urlRepository.deleteAll(urlRepository.findAll().stream()
                .filter(url -> url.getExpirationDate().isBefore(now))
                .toList()
        );
    }
}