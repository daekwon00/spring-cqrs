# spring-cqrs — PRD (Product Requirements Document)

## 프로젝트 개요

사내 인트라넷 백엔드 API 서버. CQRS 패턴을 적용한 Spring Boot 기반 RESTful API로,
인증, 게시판, 파일, 대시보드, 관리자, 시스템 관리, AI 채팅 기능을 제공한다.

## 기술 스택

- Spring Boot 4.0.3 + Java 25
- MyBatis 4.0.1 (SQL 매퍼)
- PostgreSQL (intranet DB, internal 스키마)
- Spring Security + JWT (JJWT 0.12.6)
- SpringDoc OpenAPI 3.0.2 (Swagger UI)
- Lombok

## Phase 구성

### Phase 0: 공통 인프라

- JWT Token Provider (HS256, accessToken 1h / refreshToken 7d)
- Spring Security 설정 (CORS, CSRF, STATELESS)
- JWT Authentication Filter
- 예외 처리 (GlobalExceptionHandler, BusinessException, NotFoundException)
- 공통 응답 DTO (ApiResponse, PageResponse, ErrorResponse)
- SecurityUtils (getCurrentUserId)

### Phase 1: 인증 API

- 로그인 (JWT 생성 + refresh token)
- 회원가입 (BCrypt 해싱 + 로그인 이력)
- 토큰 갱신

### Phase 2: 사용자 API

- 프로필 조회 (역할, 직급, 부서 포함)
- 프로필 수정 (이름, 이메일, 전화번호)
- 비밀번호 변경

### Phase 3: 게시판/게시글 API

- 게시판 목록/상세 (게시글 수 집계)
- 게시글 CRUD + 페이지네이션 + 검색
- 최근 게시글, 조회수 자동 증가
- 파일 첨부 연관

### Phase 4: 파일 API

- 멀티파트 파일 업로드 (UUID 네이밍)
- 파일 다운로드 (Content-Disposition)
- 파일 메타데이터 DB 저장

### Phase 5: 대시보드 API

- 통계 집계 (totalPosts, todayPosts, totalUsers, myPosts)
- 차트 데이터 (일별 게시글 수, 7일/30일)
- PostgreSQL generate_series 활용

### Phase 6: 관리자 API

- 관리자 대시보드 (통계 + 최근 가입자)
- 사용자 관리 (목록/상세/생성/수정/활성화 토글)
- 게시판 관리 (목록/생성/수정/활성화 토글)

### Phase 7: 시스템 API

- 역할 관리 (CRUD + 자동 레벨)
- 메뉴 관리 (계층 트리 구조)
- 메뉴-역할 매핑
- 공통코드 관리 (그룹 + 코드, 자동 ID 생성)
- 직급-역할 매핑
- 내 메뉴 API (역할 기반)

### AI 채팅 (SSE)

- Anthropic Claude API 직접 연동
- SSE 스트리밍 (start, delta, done, error 이벤트)
- 멀티턴 대화, 토큰 사용량 추적
