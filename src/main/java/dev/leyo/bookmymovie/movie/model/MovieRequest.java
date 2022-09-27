package dev.leyo.bookmymovie.movie.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class MovieRequest {
    private UUID movieId;
    private String movieName;
    private String imageUrl;
    private String movieDescription;
    private int maximumTickets;
    private int durationInMinutes;
    private boolean bookingStarted;
}
