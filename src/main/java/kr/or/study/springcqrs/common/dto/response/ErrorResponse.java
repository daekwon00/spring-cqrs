package kr.or.study.springcqrs.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "에러 응답")
public record ErrorResponse(

        @Schema(description = "성공 여부")
        boolean success,

        @Schema(description = "에러 메시지")
        String message,

        @Schema(description = "필드 에러 목록")
        List<FieldError> errors
) {

    public static ErrorResponse of(String message) {
        return new ErrorResponse(false, message, null);
    }

    public static ErrorResponse of(String message, List<FieldError> errors) {
        return new ErrorResponse(false, message, errors);
    }

    @Schema(description = "필드 에러")
    public record FieldError(

            @Schema(description = "필드명")
            String field,

            @Schema(description = "에러 메시지")
            String message
    ) {
    }
}
