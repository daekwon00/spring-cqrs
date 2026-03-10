package kr.or.study.springcqrs.user.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "로그인 이력 응답")
public record LoginHistoryResponse(

        @Schema(description = "로그인 IP")
        String loginIp,

        @Schema(description = "로그인 성공 여부")
        boolean loginSuccess,

        @Schema(description = "실패 사유")
        String loginFailReason,

        @Schema(description = "로그인 일시")
        LocalDateTime loginDate
) {
}
