package dev.leyo.bookmymovie.movie.service;


import dev.leyo.bookmymovie.movie.domain.MovieEntity;
import dev.leyo.bookmymovie.movie.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import static org.mockito.Mockito.*;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
public class MovieServiceTest  {

    @MockBean
    private MovieRepository repository;

    @MockBean
    private ServiceUtils utils;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(repository, utils);
    }

    @AfterEach
    void  tearDown() {
        movieService = null;
        reset(repository);
    }

    @Test
    void validateMovieBooking() {


        when(repository.findAll()).thenReturn(buildMovieEntity());

        StepVerifier.create(movieService.getMovies())
                .assertNext(movieResponse -> {
                    assertThat(movieResponse.getMovies().size()).isEqualTo(1);
                    assertThat(movieResponse.getMovies().get(0).getMovieName()).isEqualTo("Vikram");
                    assertThat(movieResponse.getMovies()).isNotNull();

                })
                .verifyComplete();
    }


    Flux<MovieEntity> buildMovieEntity() {
        UUID uuid = UUID.randomUUID();
        return  Flux.just(
                MovieEntity.builder()
                        .id(uuid)
                        .movieName("Vikram")
                        .bookingStarted(true)
                        .durationInMinutes(180)
                        .imageUrl("test/url")
                        .movieDescription("One of the best tamil movie i ever watched :D")
                        .maximumTickets(10)
                        .build()
        );
    }
}
