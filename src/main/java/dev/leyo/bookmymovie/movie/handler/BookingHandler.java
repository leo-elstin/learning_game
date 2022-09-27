package dev.leyo.bookmymovie.movie.handler;

import dev.leyo.bookmymovie.movie.model.BookingRequest;
import dev.leyo.bookmymovie.movie.model.CommonResponse;
import dev.leyo.bookmymovie.movie.service.BookingService;
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
public class BookingHandler {

    private final BookingService bookingService;

    public Mono<ServerResponse> booking(ServerRequest request) {
        log.error("'TESTING'");


        var response = request.bodyToMono(BookingRequest.class)

                .doOnNext(req -> log.error(req.getMovieDateTime().toString()))
                .flatMap(bookingService::booking)
                .doOnNext(req -> log.info(req.getStatus()));

        return ServerResponse.status(HttpStatus.CREATED).body(response, CommonResponse.class);
    }
}
