package kr.or.study.springcqrs.file.dto.query.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "파일 응답")
public record FileResponse(

        @Schema(description = "파일 ID (UUID)")
        String id,

        @Schema(description = "원본 파일명")
        String originalName,

        @Schema(description = "저장 파일명")
        String storedName,

        @Schema(description = "파일 크기 (bytes)")
        long size,

        @Schema(description = "Content-Type")
        String contentType
) {
}
