package org.example.webul.week9jpa.dto;

import org.example.webul.week9jpa.entity.movie;

import java.time.LocalDateTime;

public record movieResponse(
        Long id,
        String title,
        String director,
        Double rating,
        String genre,
        Integer runningTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static movieResponse from(movie movie) {
        return new movieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getRating(),
                movie.getGenre(),
                movie.getRunningTime(),
                movie.getCreatedAt(),
                movie.getUpdatedAt()
        );
    }
}