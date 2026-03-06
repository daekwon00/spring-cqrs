package kr.or.study.springcqrs.auth.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kr.or.study.springcqrs.auth.dto.command.request.RegisterRequest;

@Schema(description = "회원가입 요청")
public record RegisterWebRequest(

        @Schema(description = "사용자 ID", example = "newuser")
        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(min = 3, max = 50, message = "아이디는 3~50자여야 합니다.")
        String username,

        @Schema(description = "비밀번호", example = "password123")
        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 4, max = 100, message = "비밀번호는 4자 이상이어야 합니다.")
        String password,

        @Schema(description = "이름", example = "홍길동")
        @NotBlank(message = "이름을 입력해주세요.")
        String name,

        @Schema(description = "이메일", example = "user@example.com")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email
) {

    public RegisterRequest toCommand(String encodedPassword) {
        return new RegisterRequest(username, encodedPassword, name, email);
    }
}
