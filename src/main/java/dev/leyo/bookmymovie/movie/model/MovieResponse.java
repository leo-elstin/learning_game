package dev.leyo.bookmymovie.movie.model;

import dev.leyo.bookmymovie.movie.domain.MovieEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieResponse {
    private List<MovieEntity> movies;
    private String status;
}
