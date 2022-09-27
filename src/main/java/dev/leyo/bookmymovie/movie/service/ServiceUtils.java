package dev.leyo.bookmymovie.movie.service;

import dev.leyo.bookmymovie.movie.model.CommonResponse;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.util.UUID;


@Service
public class ServiceUtils {


    public CommonResponse commonResponse(@Nullable UUID uuid, String status) {
        return CommonResponse.builder()
                .id(uuid != null ? uuid.toString() : null)
                .status(status)
                .build();
    }
}
