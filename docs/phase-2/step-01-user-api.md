# Phase 2: 사용자 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/users/me | 프로필 조회 |
| PUT | /api/v1/users/me | 프로필 수정 |
| PUT | /api/v1/users/me/password | 비밀번호 변경 |

## 작업 내용

- [x] UserController + UserApi
- [x] UserCommandService — updateProfile(), changePassword()
- [x] UserQueryService — getUserProfile()
- [x] UserCommandMapper / UserQueryMapper + XML
- [x] 프로필 조회 (역할, 직급, 부서, 생성일 포함)
- [x] 비밀번호 변경 (현재 비밀번호 검증 + BCrypt)
- [x] 역할 매핑 ("ADMIN" / "USER")
