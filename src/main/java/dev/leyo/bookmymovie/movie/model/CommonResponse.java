package dev.leyo.bookmymovie.movie.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {
    private String id;
    private String status;
}
