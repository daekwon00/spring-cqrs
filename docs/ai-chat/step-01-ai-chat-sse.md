# AI 채팅 (SSE 스트리밍)

## 상태: 완료

## 엔드포인트

| Method | Path | 설명 |
|--------|------|------|
| POST | /api/v1/ai/chat | AI 채팅 (SSE) |

## 작업 내용

- [x] AiChatController + AiChatApi
- [x] AiChatService — 스트리밍 처리
- [x] Anthropic Claude API 직접 연동 (외부 라이브러리 없이)
- [x] SSE 이벤트 타입: start, delta, done, error
- [x] 멀티턴 대화 지원 (ChatMessage 리스트)
- [x] 토큰 사용량 추적 (prompt + completion)
- [x] 설정 가능: model, max-tokens, temperature
- [x] 비동기 처리 (CompletableFuture)
- [x] 에러 핸들링 (fallback 메시지)
