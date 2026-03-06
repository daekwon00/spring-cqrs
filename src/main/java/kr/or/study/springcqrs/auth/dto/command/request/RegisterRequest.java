package kr.or.study.springcqrs.auth.dto.command.request;

public record RegisterRequest(
        String userId,
        String password,
        String userName,
        String email
) {
}
