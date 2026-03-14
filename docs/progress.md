# spring-cqrs — Progress

## Phase 0: 공통 인프라

- [x] JWT Token Provider (HS256, 1h/7d)
- [x] Spring Security 설정 (CORS, CSRF, STATELESS)
- [x] JWT Authentication Filter
- [x] 예외 처리 (GlobalExceptionHandler, BusinessException, NotFoundException)
- [x] 공통 응답 DTO (ApiResponse, PageResponse, ErrorResponse)
- [x] SecurityUtils

## Phase 1: 인증 API

- [x] 로그인 (JWT 생성 + refresh token)
- [x] 회원가입 (BCrypt 해싱 + 로그인 이력)
- [x] 토큰 갱신

## Phase 2: 사용자 API

- [x] 프로필 조회
- [x] 프로필 수정
- [x] 비밀번호 변경

## Phase 3: 게시판/게시글 API

- [x] 게시판 목록/상세
- [x] 게시글 CRUD
- [x] 페이지네이션 + 검색
- [x] 최근 게시글
- [x] 조회수 자동 증가
- [x] 파일 첨부 연관

## Phase 4: 파일 API

- [x] 멀티파트 파일 업로드 (UUID 네이밍)
- [x] 파일 다운로드
- [x] 파일 메타데이터 DB 저장

## Phase 5: 대시보드 API

- [x] 통계 집계
- [x] 차트 데이터 (generate_series)

## Phase 6: 관리자 API

- [x] 관리자 대시보드 (통계 + 최근 가입자)
- [x] 사용자 관리 (목록/상세/생성/수정/활성화 토글)
- [x] 게시판 관리 (목록/생성/수정/활성화 토글)

## Phase 7: 시스템 API

- [x] 역할 관리 (CRUD + 자동 레벨)
- [x] 메뉴 관리 (계층 트리)
- [x] 메뉴-역할 매핑
- [x] 공통코드 관리 (그룹 + 코드)
- [x] 직급-역할 매핑
- [x] 내 메뉴 API

## AI 채팅 (SSE)

- [x] Anthropic Claude API 연동
- [x] SSE 스트리밍
- [x] 멀티턴 대화 + 토큰 추적
