package dev.leyo.bookmymovie.movie.handler;

import dev.leyo.bookmymovie.movie.model.CommonResponse;
import dev.leyo.bookmymovie.movie.model.MovieRequest;
import dev.leyo.bookmymovie.movie.model.MovieResponse;
import dev.leyo.bookmymovie.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieHandler {

    private final MovieService movieService;

    public Mono<ServerResponse> createMovie(ServerRequest request) {
        log.info("Movie creating started!!");

        Mono<CommonResponse> responseMono = request.bodyToMono(MovieRequest.class)
                .doOnNext(movieRequest -> log.info(movieRequest.toString()))
                .flatMap(movieService::createMovie)
                .doOnNext(commonResponse -> log.info(commonResponse.toString()));

        return ServerResponse.status(HttpStatus.CREATED).body(responseMono, CommonResponse.class);
    }

    public Mono<ServerResponse> getMovies(ServerRequest request) {

        var mono = movieService.getMovies();

        return ServerResponse.ok().body(mono, MovieResponse.class);
    }

    public Mono<ServerResponse> updateAvailability(ServerRequest request) {
        Mono<CommonResponse> responseMono = request.bodyToMono(MovieRequest.class)
                .doOnNext(movieRequest -> log.info(movieRequest.toString()))
                .flatMap(movieService::updateMovieAvailability)
                .doOnNext(commonResponse -> log.info(commonResponse.toString()));

        return ServerResponse.ok().body(responseMono, CommonResponse.class);
    }
}
