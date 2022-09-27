package dev.leyo.bookmymovie.movie.routes;

import dev.leyo.bookmymovie.movie.handler.MovieHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class MovieRoutes {

    @Bean
    public RouterFunction<ServerResponse> route(MovieHandler handler) {
        return RouterFunctions.route()
                .path("/movie", builder -> builder.POST(handler::createMovie))
                .path("/movies", builder -> builder.GET(handler::getMovies))
                .path("/movie/update", builder -> builder.PUT(handler::updateAvailability))
                .build();
    }
}
