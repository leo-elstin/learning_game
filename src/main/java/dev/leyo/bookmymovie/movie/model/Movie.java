package dev.leyo.bookmymovie.movie.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {
    private String movieName;
    private String imageUrl;
    private String movieDescription;
    private int maximumTickets;
    private int durationInMinutes;
    private boolean bookingStarted;
}
