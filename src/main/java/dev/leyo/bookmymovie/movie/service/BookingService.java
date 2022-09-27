package dev.leyo.bookmymovie.movie.service;

import dev.leyo.bookmymovie.movie.model.BookingRequest;
import dev.leyo.bookmymovie.movie.model.CommonResponse;
import dev.leyo.bookmymovie.movie.domain.BookingEntity;
import dev.leyo.bookmymovie.movie.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    public Mono<CommonResponse> booking(BookingRequest request) {
        UUID bookingId = UUID.randomUUID();

        BookingEntity bookingEntity = BookingEntity.builder()
                .id(bookingId)
                .movieDateTime(LocalDateTime.now())
                .movieName(request.getMovieName())
                .build();

        return Mono.just(bookingEntity)
                .flatMap(bookingRepository::save)
                .map(bookingDetails -> bookingResponse(bookingDetails.getId()));
    }

    private CommonResponse bookingResponse(UUID bookingId) {

        return CommonResponse.builder()
                .id(bookingId.toString())
                .status("Your booking is created!")
                .build();
    }
}
