# DK Portal API (spring-cqrs)

CQRS(Command Query Responsibility Segregation) 패턴 기반의 Spring Boot 백엔드 API 서버입니다.

## 기술 스택

| 항목 | 기술 |
|------|------|
| Framework | Spring Boot 4.0.3 |
| Language | Java 25 |
| Database | PostgreSQL |
| SQL Mapper | MyBatis |
| Auth | JWT (Access + Refresh Token) |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Build | Gradle |
| AI | Anthropic Claude / OpenAI ChatGPT / Google Gemini (SSE 스트리밍) |

## 프로젝트 구조

```
src/main/java/kr/or/study/springcqrs/
├── auth/                    # 인증 (로그인/회원가입/토큰갱신)
├── user/                    # 사용자 (프로필 조회/수정)
├── board/
│   ├── board/               # 게시판 (목록 조회)
│   └── post/                # 게시글 (CRUD + 페이징)
├── file/                    # 파일 (업로드/다운로드)
├── dashboard/               # 대시보드 (통계/차트)
├── admin/
│   ├── dashboard/           # 관리자 대시보드
│   ├── user/                # 관리자 사용자 관리
│   └── board/               # 관리자 게시판 관리
├── system/
│   ├── role/                # 역할 관리
│   ├── menu/                # 메뉴 관리 + 내 메뉴 조회
│   ├── menurole/            # 메뉴-역할 매핑
│   ├── code/                # 공통코드 관리
│   └── positionrole/        # 직급-역할 매핑
├── ai/
│   └── chat/                # AI 채팅 (SSE 스트리밍)
├── common/
│   ├── dto/                 # 공통 DTO (ApiResponse, PageRequest 등)
│   ├── enums/               # 공통 Enum
│   └── exception/           # 전역 예외 처리
└── config/
    ├── security/            # JWT + Spring Security
    └── OpenApiConfig.java   # Swagger UI 설정
```

### CQRS 모듈 구조 (각 도메인 공통)

```
{도메인}/
├── api/
│   ├── {Domain}Api.java          # Swagger 인터페이스
│   └── {Domain}Controller.java   # REST 컨트롤러
├── dto/
│   ├── web/                      # 요청 DTO (Web → Service)
│   ├── command/request/          # Command DTO (Service → Mapper)
│   └── query/
│       ├── response/             # 응답 DTO
│       └── condition/            # 조회 조건 DTO
├── mapper/
│   ├── command/                  # 변경 쿼리 (INSERT/UPDATE/DELETE)
│   └── query/                    # 조회 쿼리 (SELECT)
└── service/
    ├── command/                  # Command 서비스 (@Transactional)
    └── query/                    # Query 서비스 (읽기 전용)
```

## API 엔드포인트

### 인증 (공개)
| Method | URL | 설명 |
|--------|-----|------|
| POST | `/api/v1/auth/login` | 로그인 |
| POST | `/api/v1/auth/register` | 회원가입 |
| POST | `/api/v1/auth/refresh` | 토큰 갱신 |

### 사용자 (인증 필요)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/api/v1/users/me` | 내 프로필 조회 |
| PUT | `/api/v1/users/me` | 프로필 수정 |
| PUT | `/api/v1/users/me/password` | 비밀번호 변경 |
| GET | `/api/v1/menus/my` | 내 메뉴 조회 |

### 게시판/게시글 (인증 필요)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/api/v1/boards` | 게시판 목록 |
| GET | `/api/v1/boards/{boardId}/posts` | 게시글 목록 (페이징) |
| GET | `/api/v1/posts/{postId}` | 게시글 상세 |
| POST | `/api/v1/boards/{boardId}/posts` | 게시글 작성 |
| PUT | `/api/v1/posts/{postId}` | 게시글 수정 |
| DELETE | `/api/v1/posts/{postId}` | 게시글 삭제 |

### 파일 (인증 필요)
| Method | URL | 설명 |
|--------|-----|------|
| POST | `/api/v1/files/upload` | 파일 업로드 |
| GET | `/api/v1/files/{fileId}/download` | 파일 다운로드 |

### 대시보드 (인증 필요)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/api/v1/dashboard/stats` | 통계 조회 |
| GET | `/api/v1/dashboard/charts` | 차트 데이터 |

### 관리자 (ADMIN 권한 필요)
| Method | URL | 설명 |
|--------|-----|------|
| GET | `/api/v1/admin/dashboard/**` | 관리자 대시보드 |
| GET/PUT | `/api/v1/admin/users/**` | 사용자 관리 |
| GET/POST/PUT/DELETE | `/api/v1/admin/boards/**` | 게시판 관리 |
| GET/POST/PUT/DELETE | `/api/v1/admin/roles/**` | 역할 관리 |
| GET/POST/PUT/DELETE | `/api/v1/admin/menus/**` | 메뉴 관리 |
| GET/POST/DELETE | `/api/v1/admin/menu-roles/**` | 메뉴-역할 매핑 |
| GET/POST/PUT/DELETE | `/api/v1/admin/codes/**` | 공통코드 관리 |
| GET/POST/DELETE | `/api/v1/admin/position-roles/**` | 직급-역할 매핑 |

### AI (인증 필요)
| Method | URL | 설명 |
|--------|-----|------|
| POST | `/api/v1/ai/chat` | AI 채팅 (SSE 스트리밍) |

## 빠른 시작

```sh
# 1. 저장소 클론
git clone git@github.com:daekwon00/spring-cqrs.git
cd spring-cqrs

# 2. 로컬 DB 설정 (application-local.yaml 생성)

# 3. 빌드 및 실행
./gradlew build
./gradlew bootRun

# 4. Swagger UI 확인
# http://localhost:8081/swagger-ui/index.html
```

로그인 및 API 테스트 방법은 [HELP.md](HELP.md)를 참고하세요.

## 개발환경

| 항목 | 도구 |
|------|------|
| IDE | IntelliJ IDEA |
| 소스관리 | GitHub / Sourcetree |
| DB 클라이언트 | pgAdmin / DBeaver |

## 관련 프로젝트

- **next-portal**: Next.js 프론트엔드 (이 API의 클라이언트)
