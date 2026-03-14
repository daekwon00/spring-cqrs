# Phase 3: 게시판/게시글 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/boards | 게시판 목록 |
| GET | /api/v1/boards/{id} | 게시판 상세 |
| GET | /api/v1/boards/{boardId}/posts | 게시글 목록 (페이지네이션) |
| POST | /api/v1/posts | 게시글 생성 |
| GET | /api/v1/posts/{id} | 게시글 상세 |
| PUT | /api/v1/posts/{id} | 게시글 수정 |
| DELETE | /api/v1/posts/{id} | 게시글 삭제 |

## 작업 내용

- [x] BoardController / PostController + Api 인터페이스
- [x] BoardQueryService — 게시판 목록 (게시글 수 집계)
- [x] PostCommandService — CRUD (INSERT RETURNING 활용)
- [x] PostQueryService — 페이지네이션 + 검색 (제목/내용)
- [x] 최근 게시글 5개 조회
- [x] 조회수 자동 증가
- [x] 파일 첨부 연관 (tb_post_file)
