package kr.or.study.springcqrs.user.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "프로필 수정 요청")
public record UpdateProfileWebRequest(

        @Schema(description = "이름", example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @Schema(description = "이메일", example = "user@example.com")
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @Schema(description = "전화번호", example = "010-1234-5678")
        String phone
) {
}
