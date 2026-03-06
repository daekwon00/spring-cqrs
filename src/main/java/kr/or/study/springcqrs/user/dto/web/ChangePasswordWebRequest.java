package kr.or.study.springcqrs.user.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "비밀번호 변경 요청")
public record ChangePasswordWebRequest(

        @Schema(description = "현재 비밀번호")
        @NotBlank(message = "현재 비밀번호를 입력해주세요.")
        String currentPassword,

        @Schema(description = "새 비밀번호")
        @NotBlank(message = "새 비밀번호를 입력해주세요.")
        @Size(min = 4, max = 100, message = "비밀번호는 4자 이상이어야 합니다.")
        String newPassword
) {
}
