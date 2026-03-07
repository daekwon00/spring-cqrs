# DK Portal API - 도움말

## Swagger UI 접속

서버 실행 후 아래 URL로 API 문서 확인:

```
http://localhost:8081/swagger-ui/index.html
```

## API 인증 방법

대부분의 API는 JWT 인증이 필요합니다. Swagger UI에서 테스트하는 순서:

### 1. 로그인하여 토큰 발급

**인증 > POST /api/v1/auth/login** 을 열고 Execute:

```json
{
  "username": "admin",
  "password": "admin123"
}
```

응답에서 `accessToken` 값을 복사합니다.

### 2. Authorize 설정

1. 페이지 상단의 **Authorize** 버튼 (자물쇠 아이콘) 클릭
2. Value 입력란에 복사한 토큰 붙여넣기 (Bearer 접두사 불필요)
3. **Authorize** → **Close**

이후 모든 API를 인증된 상태로 테스트할 수 있습니다.

### 테스트 계정

| ID | 비밀번호 | 역할 | 설명 |
|----|----------|------|------|
| admin | admin123 | ADMIN | 관리자 (전체 기능 접근) |
| admin2 | admin123 | ADMIN | 운영관리자 |
| test1 | test | USER | 일반 사용자 |

### 인증 없이 접근 가능한 API

- `POST /api/v1/auth/login` - 로그인
- `POST /api/v1/auth/register` - 회원가입
- `POST /api/v1/auth/refresh` - 토큰 갱신
- Swagger UI (`/swagger-ui/**`, `/v3/api-docs/**`)

## 빌드 및 실행

```sh
# 빌드
./gradlew build

# 실행
./gradlew bootRun

# 테스트
./gradlew test

# 정리 후 재빌드
./gradlew clean build
```

## 데이터베이스 접속

```sh
PGPASSWORD='<DB_PASSWORD>' psql -h localhost -p 5432 -U admin -d intranet
```

> DB 비밀번호는 `application-local.yaml` 또는 팀 내부 문서를 참고하세요.

접속 후 스키마 설정:
```sql
SET search_path TO internal;
```

## 환경 설정

- 로컬 DB 설정: `application-local.yaml` (`.gitignore` 처리)

### AI 프로바이더 설정

3개의 AI 프로바이더를 지원하며, 환경변수로 API 키를 설정합니다:

| 프로바이더 | 환경변수 | 키 형식 | 기본 모델 | API 키 발급 |
|-----------|----------|---------|-----------|------------|
| Anthropic (Claude) | `AI_ANTHROPIC_API_KEY` | `sk-ant-api03-...` | claude-sonnet-4-20250514 | https://console.anthropic.com/settings/keys |
| OpenAI (ChatGPT) | `AI_OPENAI_API_KEY` | `sk-proj-...` | gpt-4o | https://platform.openai.com/api-keys |
| Google (Gemini) | `AI_GEMINI_API_KEY` | `AIzaSy...` | gemini-2.0-flash | https://aistudio.google.com/apikey |

3개 모두 필수가 아니며, API 키가 설정된 프로바이더만 사용 가능합니다.

#### 키 설정 방법

터미널에서 환경변수로 설정한 뒤 서버를 실행합니다:

```sh
# 방법 1: export 후 실행
export AI_ANTHROPIC_API_KEY=sk-ant-api03-xxxx
export AI_OPENAI_API_KEY=sk-proj-xxxx
export AI_GEMINI_API_KEY=AIzaSyxxxx
./gradlew bootRun

# 방법 2: 인라인으로 실행
AI_ANTHROPIC_API_KEY=sk-ant-api03-xxxx ./gradlew bootRun
```

또는 `application-local.yaml`에 직접 입력할 수도 있습니다 (gitignore 대상):

```yaml
ai:
  anthropic:
    api-key: sk-ant-api03-xxxx
  openai:
    api-key: sk-proj-xxxx
  gemini:
    api-key: AIzaSyxxxx
```

기본 프로바이더 변경: `AI_DEFAULT_PROVIDER` 환경변수 (기본값: `anthropic`)

API 요청 시 `provider` 필드로 프로바이더를 선택할 수 있습니다:

```json
{
  "provider": "openai",
  "messages": [{"role": "user", "content": "안녕하세요"}]
}
```

## 참고 문서

- [Spring Boot 4.0.3](https://docs.spring.io/spring-boot/4.0.3/)
- [MyBatis Spring Boot](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
- [SpringDoc OpenAPI](https://springdoc.org/)
