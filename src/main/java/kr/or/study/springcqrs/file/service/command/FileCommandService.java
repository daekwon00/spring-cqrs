package kr.or.study.springcqrs.file.service.command;

import kr.or.study.springcqrs.file.dto.command.request.FileCreateRequest;
import kr.or.study.springcqrs.file.dto.query.response.FileResponse;
import kr.or.study.springcqrs.file.mapper.command.FileCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileCommandService {

    private final FileCommandMapper fileCommandMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<FileResponse> uploadFiles(List<MultipartFile> files, String userId) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        List<FileResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileId = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String storedFilename = fileId + extension;
            Path filePath = uploadPath.resolve(storedFilename);

            Files.copy(file.getInputStream(), filePath);

            FileCreateRequest request = new FileCreateRequest(
                    fileId,
                    originalFilename,
                    storedFilename,
                    filePath.toString(),
                    file.getSize(),
                    file.getContentType(),
                    userId
            );
            fileCommandMapper.insertFile(request);

            responses.add(new FileResponse(
                    fileId,
                    originalFilename,
                    storedFilename,
                    file.getSize(),
                    file.getContentType()
            ));
        }

        return responses;
    }
}
