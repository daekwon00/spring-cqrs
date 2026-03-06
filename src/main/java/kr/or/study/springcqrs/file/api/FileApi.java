package kr.or.study.springcqrs.file.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.study.springcqrs.common.dto.response.ApiResponse;
import kr.or.study.springcqrs.file.dto.query.response.FileResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "파일", description = "파일 API")
public interface FileApi {

    @Operation(summary = "파일 업로드")
    ApiResponse<List<FileResponse>> uploadFiles(List<MultipartFile> files) throws IOException;

    @Operation(summary = "파일 다운로드")
    ResponseEntity<Resource> downloadFile(String fileId) throws IOException;
}
