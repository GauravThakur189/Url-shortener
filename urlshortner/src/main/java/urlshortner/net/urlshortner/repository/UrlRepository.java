package urlshortner.net.urlshortner.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import urlshortner.net.urlshortner.modal.Url;

import java.time.LocalDateTime;
import java.util.Optional;



@Repository
public interface UrlRepository extends JpaRepository<Url, Integer> {

    @Query(value = "select originalurl from Url where shorturl = ?1", nativeQuery = true)
         String findByShortUrl(String id);

    Url findByShorturl(String shortUrl);
    Optional<Url> findByShorturlAndExpirationDateAfter(String shorturl, LocalDateTime now);
}