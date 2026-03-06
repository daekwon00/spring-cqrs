package kr.or.study.springcqrs.file.api;

import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.config.security.SecurityUtils;
import kr.or.study.springcqrs.file.dto.query.response.FileResponse;
import kr.or.study.springcqrs.file.service.command.FileCommandService;
import kr.or.study.springcqrs.file.service.query.FileQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController implements FileApi {

    private final FileCommandService fileCommandService;
    private final FileQueryService fileQueryService;

    @Override
    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<List<FileResponse>> uploadFiles(
            @RequestParam("files") List<MultipartFile> files
    ) throws IOException {
        String userId = SecurityUtils.getCurrentUserId();
        List<FileResponse> responses = fileCommandService.uploadFiles(files, userId);
        return ApiResponse.ok(responses);
    }

    @Override
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws IOException {
        Map<String, Object> fileInfo = fileQueryService.getFileById(fileId);

        String filePath = (String) fileInfo.get("file_path");
        String originalName = (String) fileInfo.get("original_name");
        String contentType = (String) fileInfo.get("content_type");

        Path path = Paths.get(filePath);
        Resource resource = new UrlResource(path.toUri());

        String encodedFilename = URLEncoder.encode(originalName, StandardCharsets.UTF_8)
                .replace("+", "%20");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : "application/octet-stream")
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }
}
