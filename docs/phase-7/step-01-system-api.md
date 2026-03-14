# Phase 7: 시스템 API

## 상태: 완료

## 엔드포인트 (20+)

### 역할 관리
| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/system/roles | 역할 목록 |
| POST | /api/v1/system/roles | 역할 생성 |
| PUT | /api/v1/system/roles/{id} | 역할 수정 |
| DELETE | /api/v1/system/roles/{id} | 역할 삭제 |

### 메뉴 관리
| Method | Path | 설명 |
|--------|------|------|
| GET | /api/v1/system/menus | 메뉴 목록 (트리) |
| GET | /api/v1/menus/my | 내 메뉴 |
| POST | /api/v1/system/menus | 메뉴 생성 |
| PUT | /api/v1/system/menus/{id} | 메뉴 수정 |
| DELETE | /api/v1/system/menus/{id} | 메뉴 삭제 |

### 메뉴-역할 / 공통코드 / 직급-역할
| Method | Path | 설명 |
|--------|------|------|
| GET/PUT | /api/v1/system/menu-roles | 메뉴-역할 매핑 |
| GET/POST/PUT/DELETE | /api/v1/system/codes | 공통코드 관리 |
| GET/PUT | /api/v1/system/position-roles | 직급-역할 매핑 |

## 작업 내용

- [x] 역할 관리 (CRUD + 자동 레벨 증가)
- [x] 메뉴 관리 (계층 트리, flat → tree 변환)
- [x] 메뉴-역할 매핑 (역할별 메뉴 접근 제어)
- [x] 공통코드 관리 (코드 그룹 + 코드, 자동 ID: {groupCode}_{code})
- [x] 직급-역할 매핑 (직급별 역할 할당, 그룹핑)
- [x] 내 메뉴 API (접근 가능 메뉴 + 관리자 메뉴)
