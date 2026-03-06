package kr.or.study.springcqrs.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "페이징 응답")
public record PageResponse<T>(

        @Schema(description = "데이터 목록")
        List<T> content,

        @Schema(description = "전체 건수")
        long totalElements,

        @Schema(description = "전체 페이지 수")
        int totalPages,

        @Schema(description = "현재 페이지 (1-based)")
        int page,

        @Schema(description = "페이지 크기")
        int size
) {

    public static <T> PageResponse<T> of(List<T> content, long totalElements, int page, int size) {
        int totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
        return new PageResponse<>(content, totalElements, totalPages, page, size);
    }
}
