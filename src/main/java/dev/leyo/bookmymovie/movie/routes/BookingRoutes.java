package dev.leyo.bookmymovie.movie.routes;

import dev.leyo.bookmymovie.movie.handler.BookingHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
@Slf4j
@RestController
public class BookingRoutes {

//    @Bean
    public RouterFunction<ServerResponse> route(BookingHandler handler) {

        return RouterFunctions.route()
                .path("/booking", builderConsumer -> builderConsumer.POST(handler::booking))
                .build();
    }
}
