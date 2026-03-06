package kr.or.study.springcqrs.user.dto.command.request;

public record ChangePasswordRequest(
        String userId,
        String newPassword
) {
}
