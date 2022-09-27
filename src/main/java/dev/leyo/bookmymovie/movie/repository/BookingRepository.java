package dev.leyo.bookmymovie.movie.repository;

import dev.leyo.bookmymovie.movie.domain.BookingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface BookingRepository extends ReactiveCrudRepository<BookingEntity, UUID> {

}
