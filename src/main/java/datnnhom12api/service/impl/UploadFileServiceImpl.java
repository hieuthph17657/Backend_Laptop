package datnnhom12api.service.impl;

import datnnhom12api.service.UploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("uploadFileService")
@Transactional(rollbackFor = Throwable.class)
public class UploadFileServiceImpl implements UploadFileService {
    private final Path storageFolder = Paths.get("uploads");
    public UploadFileServiceImpl() {
        try {
            Files.createDirectories(storageFolder);
        }catch (IOException exception) {
            throw new RuntimeException("Không thể khởi tạo bộ nhớ", exception);
        }
    }


    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }
            else {
                throw new RuntimeException(
                        "Không thể đọc tệp: " + fileName);
            }
        }
        catch (IOException exception) {
            throw new RuntimeException("\n" +
                    "Không thể đọc tệp: " + fileName, exception);
        }
    }

}