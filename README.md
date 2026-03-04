# spring-cqrs

## Getting started

CQRS(Command Query Responsibility Segregation) 패턴 학습을 위한 프로젝트입니다. 아래는 개발 시작에 필요한 주요 정보들입니다.

**1. 개발환경**
- 프레임워크 : Spring Boot 4.0.3 (https://spring.io/projects/spring-boot)
- 개발 언어 : Java 25 (https://www.java.com/ko/)
- 편집 도구 : IntelliJ (https://www.jetbrains.com/idea/)
- 관리 도구 : Gradle (https://gradle.org/)
- 데이터베이스 : PostgreSQL (https://www.postgresql.org/)
- SQL Mapper : MyBatis (https://mybatis.org/)
- API 문서 : SpringDoc OpenAPI (https://springdoc.org/)

**2. 소스관리**
- 소스관리(Hosting) : GitHub (https://github.com/daekwon00/spring-cqrs)
- 소스관리(Tool) : Sourcetree (https://www.sourcetreeapp.com)

## 프로젝트 설정

1. 저장소 클론
    ```sh
    git clone git@github.com:daekwon00/spring-cqrs.git
    cd spring-cqrs
    ```

2. Gradle 빌드
    ```sh
    ./gradlew build
    ```

3. 애플리케이션 실행
    ```sh
    ./gradlew bootRun
    ```

4. Test 실행
    ```sh
    ./gradlew test
    ```

5. 정리 및 재빌드
    ```sh
    ./gradlew clean build
    ```

## 참조
```
* 편집도구는 IntelliJ 사용 권장
* DB 접속 정보는 application-local.yaml에 설정 (.gitignore 처리)
```
