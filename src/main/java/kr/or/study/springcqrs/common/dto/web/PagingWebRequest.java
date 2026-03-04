package kr.or.study.springcqrs.common.dto.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import kr.or.study.springcqrs.common.enums.SortOrder;

@Schema(description = "페이징 공통 파라미터")
public record PagingWebRequest(

        @Schema(description =  "페이지 번호", example = "1")
        @Min(1) Integer page,

        @Schema(description = "페이지 사이즈", example = "10")
        @Min(1) Integer pageSize,

        @Schema(description = "정렬 순서", example = "DESC")
        SortOrder sortOrder
) {
     public PagingWebRequest {
        if (page == null) {
            page = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if (sortOrder == null) {
            sortOrder = SortOrder.DESC;
        }
     }
}
