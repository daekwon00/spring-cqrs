# Phase 1: 인증 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| POST | /api/v1/auth/login | 로그인 |
| POST | /api/v1/auth/register | 회원가입 |
| POST | /api/v1/auth/refresh | 토큰 갱신 |

## 작업 내용

- [x] AuthController + AuthApi (Swagger)
- [x] AuthCommandService — login(), register()
- [x] AuthQueryService — 사용자 조회
- [x] AuthCommandMapper / AuthQueryMapper + XML
- [x] JWT 생성 (accessToken + refreshToken)
- [x] BCrypt 비밀번호 해싱
- [x] 로그인 이력 추적 (성공/실패, IP)
- [x] 듀얼 비밀번호 처리 (BCrypt + 평문 호환)
