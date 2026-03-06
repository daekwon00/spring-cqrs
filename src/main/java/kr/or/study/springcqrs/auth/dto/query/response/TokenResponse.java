package kr.or.study.springcqrs.auth.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "토큰 갱신 응답")
public record TokenResponse(

        @Schema(description = "새 액세스 토큰")
        String accessToken
) {
}
