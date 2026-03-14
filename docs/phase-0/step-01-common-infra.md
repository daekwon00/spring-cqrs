# Phase 0: 공통 인프라

## 상태: 완료

## 작업 내용

- [x] JWT Token Provider
  - HS256 알고리즘
  - accessToken 1시간, refreshToken 7일
  - JJWT 0.12.6 라이브러리
- [x] Spring Security 설정
  - CORS (localhost:3000 허용)
  - CSRF 비활성화
  - STATELESS 세션 관리
  - ROLE_ADMIN, ROLE_SUPER_ADMIN, ROLE_MEMBER 지원
- [x] JWT Authentication Filter
  - Bearer 토큰 파싱
  - SecurityContext 설정
- [x] 예외 처리
  - GlobalExceptionHandler (전역)
  - BusinessException (비즈니스 로직)
  - NotFoundException (404)
  - ErrorResponse DTO
- [x] 공통 응답 DTO
  - ApiResponse\<T\> — 단일 응답
  - PageResponse\<T\> — 페이지네이션 응답
  - PageCondition — 페이징 파라미터
- [x] SecurityUtils — getCurrentUserId() 헬퍼
- [x] PostgreSQL + MyBatis 설정
