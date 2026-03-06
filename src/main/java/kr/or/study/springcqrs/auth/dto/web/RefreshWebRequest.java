package kr.or.study.springcqrs.auth.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "토큰 갱신 요청")
public record RefreshWebRequest(

        @Schema(description = "리프레시 토큰")
        @NotBlank(message = "리프레시 토큰을 입력해주세요.")
        String refreshToken
) {
}
