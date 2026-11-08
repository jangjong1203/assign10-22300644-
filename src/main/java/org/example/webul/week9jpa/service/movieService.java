package org.example.webul.week9jpa.service;

import org.example.webul.week9jpa.dto.movieRequest;
import org.example.webul.week9jpa.dto.movieResponse;
import org.example.webul.week9jpa.entity.movie;
import org.example.webul.week9jpa.repository.movieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
/*

Q7. save()를 호출하면 새로운 데이터 등록과 기존 데이터 수정이 어떻게 처리되는가?

받아온 객체의 id값이 비어있다면 새데이터로 판단하고 데이터 등록으로 처리합니다.
id값이 존재한다면 데이터 수정으로 처리합니다.

Q8. @Transactional과 Dirty Checking은 어떤 관계가 있는가?

Dirty Checking은 DB에서 가져온 객체의 값을 바꾸면, save()를 호출하지 않아도
JPA가 변경된 내용을 감지해서 자동으로 수정해주는 기능 입니다.
JPA가 변경을 감지하는 기능은 트랙젝션이 끝나고 Commit이 될때만 작동하기때문에
메서드에 @Transactional을 붙여주어야만 변경을 감지할수있고, Dirty Checking이 정상적으로 작동합니다.

 */




@Service
public class movieService {
    private final movieRepository repository;

    public movieService(movieRepository repository){
        this.repository=repository;
    }

    public movieResponse create(movieRequest request) {
        movie saved = repository.save(new movie(request));
        return movieResponse.from(saved);
    }

    public List<movieResponse> findAll(){
        List<movie> movies = repository.findAll();
        List<movieResponse> response = new ArrayList<>();
        for (movie movie : movies) {
            response.add(movieResponse.from(movie));
        }
        return response;
    }
    public movieResponse findById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + id);
        }
        movie movie = repository.findById(id).get();
        return movieResponse.from(movie);
    }
    @Transactional
    public movieResponse update(Long id, movieRequest request) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + id);
        }
        movie m = repository.findById(id).get();
        m.update(request);
        return movieResponse.from(m);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found: " + id);
        }
        repository.deleteById(id);
    }


    public List<movie> searchByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    public List<movie> searchByRatingAndMinTime(Double rating, Integer runningTime) {
        return repository.findByRatingGreaterThanEqualAndRunningTimeGreaterThanEqual(rating, runningTime);
    }

    public List<movie> searchByTitleOrderByRating(String keyword) {
        return repository.findByTitleContainingOrderByRatingDesc(keyword);
    }
}

