package kr.or.study.springcqrs.auth.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보 응답")
public record UserInfoResponse(

        @Schema(description = "사용자 ID", example = "admin")
        String username,

        @Schema(description = "이름", example = "관리자")
        String name,

        @Schema(description = "이메일", example = "admin@example.com")
        String email,

        @Schema(description = "역할", example = "ADMIN")
        String role
) {
}
