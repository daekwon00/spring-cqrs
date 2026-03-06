package kr.or.study.springcqrs.user.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "사용자 프로필 응답")
public record UserProfileResponse(

        @Schema(description = "사용자 ID", example = "admin")
        String id,

        @Schema(description = "사용자 ID", example = "admin")
        String username,

        @Schema(description = "이름", example = "관리자")
        String name,

        @Schema(description = "이메일", example = "admin@example.com")
        String email,

        @Schema(description = "전화번호")
        String phone,

        @Schema(description = "부서")
        String department,

        @Schema(description = "직급")
        String position,

        @Schema(description = "역할", example = "ADMIN")
        String role,

        @Schema(description = "가입일")
        LocalDateTime createdAt
) {
}
