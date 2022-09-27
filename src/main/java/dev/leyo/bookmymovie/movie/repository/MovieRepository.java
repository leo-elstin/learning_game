package dev.leyo.bookmymovie.movie.repository;

import dev.leyo.bookmymovie.movie.domain.MovieEntity;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MovieRepository extends ReactiveCrudRepository<MovieEntity, UUID> {

    @Modifying
    @Query(value = "UPDATE movies SET booking_started = :status WHERE movie_id = :movie_id")
    Mono<Integer> updateMovie(boolean status, UUID movie_id);
}
