# Phase 6: 관리자 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/admin/dashboard/stats | 관리자 통계 |
| GET | /api/v1/admin/dashboard/recent-users | 최근 가입자 |
| GET | /api/v1/admin/users | 사용자 목록 |
| GET | /api/v1/admin/users/{id} | 사용자 상세 |
| POST | /api/v1/admin/users | 사용자 생성 |
| PUT | /api/v1/admin/users/{id} | 사용자 수정 |
| PATCH | /api/v1/admin/users/{id}/toggle-active | 활성화 토글 |
| GET | /api/v1/admin/boards | 게시판 목록 |
| POST | /api/v1/admin/boards | 게시판 생성 |
| PUT | /api/v1/admin/boards/{id} | 게시판 수정 |
| PATCH | /api/v1/admin/boards/{id}/toggle-active | 활성화 토글 |

## 작업 내용

- [x] AdminDashboardController / AdminUserController / AdminBoardController
- [x] 관리자 대시보드 (총 사용자, 오늘 가입, 활성 게시판, 오늘 게시글)
- [x] 최근 가입자 5명
- [x] 사용자 관리 (페이지네이션 + 검색)
- [x] 게시판 관리 (게시글 수 LEFT JOIN 집계)
- [x] SecurityConfig — ROLE_SUPER_ADMIN 지원
