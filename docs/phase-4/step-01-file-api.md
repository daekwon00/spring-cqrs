# Phase 4: 파일 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| POST | /api/v1/files/upload | 파일 업로드 |
| GET | /api/v1/files/{id}/download | 파일 다운로드 |

## 작업 내용

- [x] FileController + FileApi
- [x] FileCommandService — upload (UUID 파일명 변환)
- [x] FileQueryService — download (Content-Disposition 헤더)
- [x] FileCommandMapper / FileQueryMapper + XML
- [x] 파일 메타데이터 DB 저장 (originalName, storedName, size, contentType)
- [x] 절대 경로 변환 (Tomcat 호환)
- [x] 설정 가능한 업로드 디렉토리 (./uploads)
