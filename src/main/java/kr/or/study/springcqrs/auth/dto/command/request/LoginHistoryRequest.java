package kr.or.study.springcqrs.auth.dto.command.request;

public record LoginHistoryRequest(
        String userId,
        String loginIp,
        boolean loginSuccess,
        String loginFailReason
) {
}
