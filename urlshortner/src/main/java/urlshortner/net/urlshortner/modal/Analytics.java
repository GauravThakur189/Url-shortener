package urlshortner.net.urlshortner.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "analytics")
@Data
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "url_id")
    @JsonBackReference
    private Url url;

    private int totalVisits = 0;

    @ElementCollection
    private Set<String> uniqueVisitors = new HashSet<>();

    private int desktopVisits = 0;
    private int mobileVisits = 0;

    @ElementCollection
    private Set<LocalDateTime> visitTimestamps = new HashSet<>();

    private String topReferrer;

    public void incrementTotalVisits() {
        this.totalVisits++;
    }

    public void addUniqueVisitor(String visitorHash) {
        this.uniqueVisitors.add(visitorHash);
    }

    public void addVisitTimestamp(LocalDateTime timestamp) {
        this.visitTimestamps.add(timestamp);
    }

    public void incrementDesktopVisits() {
        this.desktopVisits++;
    }

    public void incrementMobileVisits() {
        this.mobileVisits++;
    }
}