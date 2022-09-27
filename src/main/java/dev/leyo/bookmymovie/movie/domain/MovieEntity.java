package dev.leyo.bookmymovie.movie.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table(value = "movies")
public class MovieEntity implements Persistable<UUID> {

    @Id
    @Column(value = "movie_id")
    private UUID id;
    private String movieName;
    private String imageUrl;
    private String movieDescription;
    private int maximumTickets;
    @Column(value = "movie_duration")
    private int durationInMinutes;
    private boolean bookingStarted;

    @Override
    public boolean isNew() {
        return true;
    }
}
