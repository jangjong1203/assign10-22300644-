프로젝트 소개

    프로젝트 주제: 9주차에 개발한 영화관리 프로젝트를 기반으로 
    검색,정렬, 자동 저장, 수정, 생성 시간 기록 기능을 추가한 프로젝트 입니다.
    주요 기능:
      영화목록 생성,수정,삭제,조회 
      Query Method를 사용한 영화 이름검색조회, 평점 및 상영시간 조건 조회, 
      @Transactional과 Dirty Checking을 통한 자동 영화 정보 수정
      JPA Auditing을 이용한 생성 시간 및 수정 시간 저장
    GitHub Repository URL: https://github.com/2026-2-WebService/assign10-c01-22300644
    Render Deployment URL: https://assign10-22300644.onrender.com/

주요 구현 내용 및 결과

Query Method 3개 구현 및 API 실행 결과

    1. 단일 조건 검색
        검색 조건: 입력받은 장르와 일치하는 영화 목록 조회
        API 요청: GET /api/movie/search/genre?genre=SF
        실행 결과:

    2. 복합 조건 검색
        검색 조건: 평점이 입력값 이상이고 상영시간도 기준 시간 이상인 영화 조회
        API 요청: GET /api/movie/search/filter?rating=9.0&runningTime=145
        실행 결과: 

    3. 정렬 포함 검색
        검색 조건: 영화 제목에 키워드가 포함된 영화를 찾아 평점이 높은 순서로 정렬하여 조회
        API 요청: GET /api/movie/search/title?keyword=in
        실행 결과: 

    2) Dirty Checking 수정 기능 구현 및 실행 결과
        구현 내용: movieService의 update()에 @Transactional을 적용하고, 
        기존에 있던 repository.save(m)코드는 제거한후, repository.findById(id).get()로 가져온 객체를
        m.update(request)만 호출한뒤 바로 반환.
        실행 결과: 

    3) Auditing 생성·수정 시간 자동 기록 결과
        구현 내용: JpaApplication 클래스에 @EnableJpaAuditing을 추가하고,
        movie 엔티티에 @EntityListeners(AuditingEntityListener.class)와 
        @CreatedDate(createdAt), @LastModifiedDate(updatedAt)필드, 게터 추가.
        실행 결과
        
Key Learning

    Query Method 활용: SQL 문을 직접 작성하지 않아도 Repository에 규칙에 맞는 이름만 적어주면
    JPA가 쿼리를 만들어 준다는 것을 배웠습니다.
    Dirty Checking의 원리: JPA에서는 데이터를 수정할 때  save()를 호출할 필요 없이,
    @Transactional 안에서 가져온 객체의 값을 바꾸면 트랜잭션이 Commit될때 자동으로 변경을 감지해 자동으로 수정해 준다는 점을 배웠습니다.
    JPA Auditing: 데이터가 언제 만들어지고 언제 수정되었는지를 직접 시간을 넣지 않고 어노테이션 설정만으로 자동 관리하는 방법을 배웠습니다.

    Problem & Solution
    Query Method 방식을 처음 써보다 보니 어려웠습니다. 하지만 교수님이 주신 문제들을 직접 풀어보니 생각보다
    해당 방식에 대해 쉽게 이해 할 수 있었던 것 같습니다.

    Reflection
    Query Method를 다뤄 보면서 메서드 이름만으로 이렇게 작동이 된다는게 조금 신기하기도 했습니다.
    그리고 메서드랑 SQL문 변환 문제를 풀면서 Query Method 방식 뿐만 아니라 SQL문에 대한 이해도 늘었던것 같습니다. 

AI Usage
    
    render 배포시 자꾸 오류가 발생해 해당 코드를 AI를 활용하여 해결하였습니다.
    gradle-wrapper.properties 파일의 networkTimeout가 10초로 설정이 되어있어 응답에 오래 걸리면 오류가 발생한다고하여 60초로 늘리니 정상적으로 작동되었습니다.

    AI 사용 비율: 20% :메서드랑 SQL문 변환공부 및 제가 작성한게 맞는지 확인 하는 용도 및 render 배포때 오류 해결용도로 사용하였습니다.  