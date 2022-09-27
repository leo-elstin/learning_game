package dev.leyo.bookmymovie.movie.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
    /*
    All together now: A shortcut for @ToString, @EqualsAndHashCode,
    @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
    https://projectlombok.org/features/Data
    */
@Builder
    /* Project Lombok's @Builder is a helpful mechanism for
    using the Builder pattern without writing boilerplate code.
    We can apply this annotation to a Class or a method.
    https://www.baeldung.com/lombok-builder
    https://www.baeldung.com/creational-design-patterns#builder
    */
@AllArgsConstructor
@Table(value = "bookings")
public class BookingEntity implements Persistable<UUID> {
    @Id
    private UUID id;
    @Column(value = "movieName")
    private String movieName;
    @Column(value = "movieDataTime")  // leaving the  column name empty will check from snake case names
    private LocalDateTime movieDateTime;


    @Override
    public boolean isNew() {
        return true;
    }
}
