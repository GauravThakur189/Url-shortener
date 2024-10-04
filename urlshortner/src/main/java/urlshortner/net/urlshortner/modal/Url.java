package urlshortner.net.urlshortner.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "shorturl")
    private String shorturl;

    @Column(nullable = false,unique = true,length = 2048 , name = "originalurl")
    private String originalurl;


    @OneToOne(mappedBy = "url", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Analytics analytics;


    public Url() {
        this.analytics = new Analytics();
        this.analytics.setUrl(this);
    }

    private LocalDateTime createdAt;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

}