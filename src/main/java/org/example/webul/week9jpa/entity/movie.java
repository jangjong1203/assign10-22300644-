package org.example.webul.week9jpa.entity;

import jakarta.persistence.*;
import org.example.webul.week9jpa.dto.movieRequest;
/*
① Entity
Q1. @Entity, @Id, @GeneratedValue는 각각 어떤 역할을 하는가?
@Entity: JPA가 관리 하는 클래스임을 선언합니다. JPA가 관리 하게 된다면
DB의 테이블과 매핑이 되고 JPA각 객체의 변화상태를 관리 하게 됩니다.
@Id: 해당하는 필드를 DB의 테이블의 Primary Key로 지정하여 각 데이터의 row를
구분하는 고유한 값이 됩니다.
@GeneratedValue: Primary Key값을 코드로 지정해주지 않고 DB가 자동적으로 생성하게 합니다.

Q2. Entity 클래스와 데이터베이스 테이블은 어떻게 연결되는가?

@Entity를 통해 연결됩니다. 프로그램실행시 @Entity가 붙어있는 클래스를 DB의 테이블과 연결시키고,
@Table 을 사용하면 특정한 이름의 테이블과 연결시킬수도 있고 사용하징 않는다면, 클래스와 이름이 동일한
테이블과 연결됩니다. 클래스 내부의 각 필드들은 각 테이블의 column들과 매핑됩니다.
runningTime와 같은 필드이름은 running_time 과 같이 변경되어 DB에 매핑됩니다.
*/

@Entity
@Table(name="moviejpa")
public class movie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false,length = 100)
    private String director;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false,length = 50)
    private String genre;

    @Column(nullable = false)
    private Integer runningTime;

    protected movie() { }

    public movie(movieRequest request) {
        this.title = request.title();
        this.director = request.director();
        this.rating = request.rating();
        this.genre = request.genre();
        this.runningTime = request.runningTime();
    }

    public void update(movieRequest request) {
        this.title = request.title();
        this.director = request.director();
        this.rating = request.rating();
        this.genre = request.genre();
        this.runningTime = request.runningTime();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getRunningTime() {
        return runningTime;
    }
}

