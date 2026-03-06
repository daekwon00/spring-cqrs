package kr.or.study.springcqrs.file.dto.command.request;

public record FileCreateRequest(
        String fileId,
        String originalFilename,
        String storedFilename,
        String filePath,
        long fileSize,
        String contentType,
        String createdBy
) {
}
