package kr.or.study.springcqrs.admin.user.dto.query.condition;

public record AdminUserSearch(
        int page,
        int size,
        String search
) {
    public int offset() {
        return page * size;
    }
}
