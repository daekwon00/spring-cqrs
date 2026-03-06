package kr.or.study.springcqrs.user.dto.command.request;

public record UpdateProfileRequest(
        String userId,
        String userName,
        String email,
        String phone
) {
}
