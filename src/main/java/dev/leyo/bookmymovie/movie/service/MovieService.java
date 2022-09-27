package dev.leyo.bookmymovie.movie.service;


import dev.leyo.bookmymovie.movie.model.CommonResponse;
import dev.leyo.bookmymovie.movie.model.MovieRequest;
import dev.leyo.bookmymovie.movie.domain.MovieEntity;
import dev.leyo.bookmymovie.movie.model.MovieResponse;
import dev.leyo.bookmymovie.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    final MovieRepository movieRepository;
    final ServiceUtils utils;

    public Mono<CommonResponse> createMovie(MovieRequest request) {
        return Mono.just(movieEntity(request))
                .flatMap(movieEntity -> movieRepository.save(movieEntity)
                        .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(10))))
                .map(movieEntity -> utils.commonResponse(movieEntity.getId(), "Movie added successfully!!"));
    }

    public Mono<MovieResponse> getMovies() {
        Flux<MovieEntity> movieEntity = movieRepository.findAll();


        return movieEntity.collectList().map(this::movieResponse);
    }

    public Mono<CommonResponse> updateMovieAvailability(MovieRequest request) {

        return movieRepository.findById(request.getMovieId())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Id not found!!")))
                .flatMap(value -> movieRepository.updateMovie(request.isBookingStarted(), request.getMovieId()))
                .doOnError(error -> log.error(error.getMessage()))
                .onErrorMap(Exception.class, error -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .doOnNext(integer -> log.error(integer.toString()))
                .map(integer -> utils
                        .commonResponse(null, "Booking " + (integer == 0 ? "Stopped" : "Started")));
    }


    private MovieEntity movieEntity(MovieRequest request) {
        UUID movieId = UUID.randomUUID();
        return MovieEntity.builder()
                .id(movieId)
                .movieName(request.getMovieName())
                .bookingStarted(request.isBookingStarted())
                .movieDescription(request.getMovieDescription())
                .durationInMinutes(request.getDurationInMinutes())
                .imageUrl(request.getImageUrl())
                .maximumTickets(request.getMaximumTickets())
                .build();
    }

    private MovieResponse movieResponse(List<MovieEntity> entities) {
        return MovieResponse.builder()
                .status("Success")
                .movies(entities)
                .build();
    }
}
