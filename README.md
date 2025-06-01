# Graduable - 졸업 시뮬레이터

졸업 시뮬레이터는 학생들이 자신의 졸업 요건을 확인하고, 남은 학점을 계산하며, 졸업까지의 로드맵을 계획할 수 있도록 도와주는 웹 애플리케이션입니다.

## 주요 기능

- **졸업 요건 확인**
  - 전공/교양 필수/선택 학점 요건 확인
  - 이수한 과목과 남은 과목 확인
  - 졸업까지 필요한 학점 계산

- **로드맵 계획**
  - 학기별 수강 계획 작성
  - 과목 검색 및 추가
  - 로드맵 저장 및 관리

- **성적 관리**
  - 이수한 과목의 성적 입력
  - 학점 평균 계산
  - 성적 이수 현황 확인

## 기술 스택

### Backend
- **언어 및 프레임워크**
  - Java 17
  - Spring Boot 3.4.4
  - Spring Data JPA

- **데이터베이스**
  - H2 Database (개발)
  - MySQL (운영)

- **빌드 도구**
  - Gradle 8.5

- **테스트**
  - JUnit 5
  - Mockito
  - Spring Test

- **문서화**
  - Swagger/OpenAPI 3.0
  - Spring REST Docs

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/software/graduable/
│   │       ├── config/         # 설정 파일
│   │       │   └── SwaggerConfig.java
│   │       ├── course/         # 과목 관련
│   │       │   ├── controller/
│   │       │   ├── service/
│   │       │   ├── repository/
│   │       │   └── dto/
│   │       ├── global/         # 전역 설정
│   │       │   ├── exception/
│   │       │   └── response/
│   │       ├── grade/          # 성적 관련
│   │       ├── plannedCourse/  # 계획된 과목
│   │       ├── roadmap/        # 로드맵
│   │       ├── simulator/      # 시뮬레이터
│   │       ├── user/           # 사용자
│   │       └── viewPdf/        # PDF 뷰어
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       └── application-prod.yml
└── test/                       # 테스트 코드
```

## 테스트

프로젝트는 TDD(Test-Driven Development) 방식으로 개발되었으며, 다음과 같은 테스트를 포함합니다:

### 단위 테스트
- **User 도메인**
  - 사용자 생성 및 정보 수정 테스트
  - 사용자 서비스 테스트

- **Course 도메인**
  - 과목 생성 및 정보 테스트
  - 과목 검색 및 필터링 테스트

- **Grade 도메인**
  - 성적 엔티티 생성 및 DTO 변환 테스트
  - 학점 계산 로직 테스트

- **PlannedCourse 도메인**
  - 계획된 과목 생성 테스트
  - 학년도/학기 관리 테스트
  - 과목 충돌 검사 테스트

- **Roadmap 도메인**
  - 로드맵 DTO 생성 테스트
  - 학기별 로드맵 관리 테스트
  - 로드맵 유효성 검사 테스트

- **Simulator 도메인**
  - 졸업 시뮬레이션 요청/응답 테스트
  - 로드맵 저장 테스트
  - 졸업 요건 충족 여부 테스트

### 통합 테스트
- API 엔드포인트 테스트
- 데이터베이스 연동 테스트
- 외부 서비스 연동 테스트

### 테스트 실행 방법
```bash
# 전체 테스트 실행
./gradlew test

# 특정 테스트 클래스만 실행
./gradlew test --tests "com.software.graduable.user.UserServiceTest"

# 테스트 커버리지 리포트 생성
./gradlew jacocoTestReport
```

## 시작하기

### 요구사항
- Java 17
- Gradle 8.5
- MySQL 8.0 (운영 환경)

### 설치 및 실행

1. 저장소 클론
```bash
git clone https://github.com/your-username/graduable.git
cd graduable
```

2. 개발 환경 설정
```bash
cp src/main/resources/application-dev.yml.example src/main/resources/application-dev.yml
# application-dev.yml 파일에서 데이터베이스 설정 수정
```

3. 애플리케이션 실행
```bash
./gradlew bootRun
```

4. 테스트 실행
```bash
./gradlew test
```

## API 문서

API 문서는 Swagger UI를 통해 확인할 수 있습니다:
```
http://localhost:8080/swagger-ui.html
```