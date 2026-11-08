package org.example.webul.week9jpa.controller;

import org.example.webul.week9jpa.dto.movieRequest;
import org.example.webul.week9jpa.dto.movieResponse;
import org.example.webul.week9jpa.entity.movie;
import org.example.webul.week9jpa.service.movieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class movieController {
    private movieService service;
    public movieController(movieService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<movieResponse> create(@RequestBody movieRequest request) {
        movieResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/movie/" + created.id())).body(created);
    }

    @GetMapping
    public List<movieResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public movieResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public movieResponse update(@PathVariable Long id, @RequestBody movieRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/genre")
    public List<movie> searchByGenre(@RequestParam String genre) {
        return this.service.searchByGenre(genre);
    }

    @GetMapping("/search/filter")
    public List<movie> searchByRatingAndRunningTime(
            @RequestParam Double rating,
            @RequestParam Integer runningTime) {
        return this.service.searchByRatingAndMinTime(rating, runningTime);
    }

    @GetMapping("/search/title")
    public List<movie> searchByTitleOrderByRating(@RequestParam String keyword) {
        return this.service.searchByTitleOrderByRating(keyword);
    }

}
