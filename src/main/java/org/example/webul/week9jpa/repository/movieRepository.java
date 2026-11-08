package org.example.webul.week9jpa.repository;

import org.example.webul.week9jpa.entity.movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
Q3. JpaRepository<Entity, Long>에서 두 타입은 각각 무엇을 의미하는가?
Entity(movie): 현재 클래스에서 다룰 Entity가 어떤 타입인지 의미합니다.
Long: 해당 Entity클래스에서 @ID 즉 Primary Key의 타입이 무엇인지 의미합니다.

Q4. JpaRepository에서 기본 제공하는 CRUD 메서드를 5개 이상 찾아 메서드명과 역할을 주석으로 작성하세요.
1. save(S entity): 새로운 entity는 DB에 저장하고, 존재하는 entity는 수정합니다.
2. findById(ID id): id 값을 기준으로 엔티티를 조회하고 Optional타입으로 반환합니다.
3. findAll(): 테이블에 저장된 모든 entity를 리스트 타입으로 반환합니다.
4. deleteById(ID id): id 값을 기준으로 엔티티를 찾아 테이블에서 삭제합니다.
5. count(): 테이블에 저장된 데이터의 개수 반환합니다.
6. existsById(ID id): 해당 id 값을 가진 데이터가 DB에 존재하는지 여부를 true,false로 반환합니다.

Q5. findById()의 반환 타입이 Optional인 이유는 무엇인가?

만약 찾는 id값을 가진 데이터가 없다면 null을 반환 해야하는데 그렇게 되면 에러가 발생할수있습니다.
하지만 Optional 타입을 반환 하게 된다면 .orElseThrow 와 같은 메서드를 이용해서 데이터가 없을때의
상황을 에러가 나지않게 처리 해줄 수 있습니다.

Q6. JpaRepository에서 기본 제공하는 메서드와 개발자가 직접 작성하는 Query Method의 차이는 무엇인가?

기본 제공 되는 메서드는 Primaty Key를 통해서만 생성,조회,수정,삭제 등이 가능하지만,
직접 작성하는 Query Method는 Primaty Key뿐만 아니라 다른 필드들로도 조회,삭제 등이 가능합니다..


*/

public interface movieRepository extends JpaRepository<movie, Long> {

    List<movie> findByGenre(String genre);

    List<movie> findByRatingGreaterThanEqualAndRunningTimeGreaterThanEqual(Integer rating,Integer runningTime );

    List<movie> findByTitleContainingOrderByRatingDesc(String keyword);
}
