# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run Commands

- Build the application: `./gradlew build`
- Run the application: `./gradlew bootRun`
- Run all tests: `./gradlew test`
- Run a specific test: `./gradlew test --tests "kr.or.study.springcqrs.SomeTest"`
- Clean and rebuild: `./gradlew clean build`

## Database

- The application uses PostgreSQL with MyBatis as the SQL mapper
- Local development config is in application-local.yaml (.gitignore)
- Dev/Prod environments use environment variables: `${DB_URL}`, `${DB_USERNAME}`, `${DB_PASSWORD}`
- Local DB connection:
  - URL: `jdbc:postgresql://localhost:5432/intranet?currentSchema=internal`
  - Username: `admin`
  - Password: `qwer!@34`
  - Database: `intranet`
  - Schema: `internal`
- CLI access: `PGPASSWORD='qwer!@34' psql -h localhost -p 5432 -U admin -d intranet`

## Project Architecture

### CQRS Pattern (Command Query Responsibility Segregation)

- **Command**: 데이터 변경 (Create, Update, Delete) 처리
- **Query**: 데이터 조회 (Read) 처리
- Command와 Query를 분리하여 각각 독립적으로 최적화

### Backend Architecture (Spring Boot 4)

- **Controller Layer**: REST controllers (@RestController) for API endpoints
- **Service Layer**: Command/Query 분리된 서비스 계층
- **Mapper Layer**: MyBatis mappers (interface + XML)
- **DTO Layer**: Command/Query 별도 DTO 사용
- **Validation**: Spring Validation for request validation
- **API Docs**: SpringDoc OpenAPI (Swagger UI)

## Tech Stack

- Java 25
- Spring Boot 4.0.3
- MyBatis
- PostgreSQL
- Lombok
- SpringDoc OpenAPI 3.0.2
- JUnit 5

## Testing

- JUnit 5 for test implementation
- Spring Boot Test for integration testing

## Git Commit Rules

- Claude는 직접 `git commit`을 실행하지 않는다
- 작업이 끝나면 커밋 메시지를 제안하고 "커밋할까요?"라고 사용자에게 확인한다
- 사용자가 "gogo", "commit", 또는 진행하라는 의미의 답변을 하면 커밋을 실행한다
- 커밋 메시지에 `Co-Authored-By` 줄을 추가하지 않는다
- 커밋 메시지는 한글로 작성한다
- `.gitignore` 수정이 필요한 경우, 변경 전 사용자에게 확인한다

## Development Practices

- CQRS 패턴에 따라 Command/Query 책임 분리
- DTOs are used for data transfer between layers
- MyBatis mappers are defined as interfaces with XML query definitions
- Transactional boundaries are defined at the service layer
- 한국어 주석 사용 가능

## SDD (Spec-Driven Development)

- `prd.md` — 프로젝트 요구사항 정의
- `docs/progress.md` — Phase/Step별 진행 체크리스트
- `docs/phase-N/step-NN-*.md` — 단계별 작업 명세
- 새 작업 시작 전 progress.md 확인, 완료 시 체크 표시 업데이트
