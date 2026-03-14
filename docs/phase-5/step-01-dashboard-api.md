# Phase 5: 대시보드 API

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/dashboard/stats | 통계 |
| GET | /api/v1/dashboard/chart | 차트 데이터 |

## 작업 내용

- [x] DashboardController + DashboardApi
- [x] DashboardQueryService — getStats(), getChartData()
- [x] DashboardQueryMapper + XML
- [x] 통계: totalPosts, todayPosts, totalUsers, myPosts
- [x] 차트: 일별 게시글 수 (7일/30일 기간 설정)
- [x] PostgreSQL generate_series로 빈 날짜 채움
- [x] @Transactional(readOnly = true)
