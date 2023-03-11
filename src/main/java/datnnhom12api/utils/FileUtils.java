package datnnhom12api.utils;

import datnnhom12api.exceptions.CustomException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Component
public class FileUtils {
    public static Path storageFolder = Paths.get("uploads");

    public FileUtils() throws CustomException {
        try {
            Files.createDirectories(storageFolder);
        } catch (IOException exception) {
            throw new CustomException(403, "Không thể khởi tạo bộ nhớ");
        }
    }

    private static boolean isImageFile(MultipartFile file) {
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp", "gif", "tiff", "psd", "pdf", "eps", "ai", "indd", "raw"})
                .contains(fileExtension.trim().toLowerCase());
    }

    public String setImageSave(MultipartFile imageName) throws CustomException {
        if (imageName.isEmpty()) {
            throw new CustomException(403, "Không thể lưu trữ tệp trống.");
        }
        if (!isImageFile(imageName)) {
            throw new CustomException(403, "Bạn chỉ có thể tải lên tệp hình ảnh");
        }
        float fileSizeInMegabytes = imageName.getSize() / 1_000_000.0f;
        if (fileSizeInMegabytes > 5.0f) {
            throw new CustomException(403, "Tệp phải <= 5MB");
        }
        String fileExtension = FilenameUtils.getExtension(imageName.getOriginalFilename());
        String generatedFileName = UUID.randomUUID().toString().replace("-", "");
        generatedFileName = generatedFileName + "." + fileExtension;
        Path destinationFilePath = this.storageFolder.resolve(
                        Paths.get(generatedFileName))
                .normalize().toAbsolutePath();
        if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
            throw new CustomException(403, "Không thể lưu trữ tệp bên ngoài thư mục hiện tại.");
        }
        try (InputStream inputStream = imageName.getInputStream()) {
            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:8080/v1/FileUpload/files/"+generatedFileName;
    }

    public String setImageUpdate(MultipartFile imageName) throws CustomException {
        float fileSizeInMegabytes = imageName.getSize() / 1_000_000.0f;
        if (fileSizeInMegabytes > 5.0f) {
            throw new CustomException(403, "Tệp phải <= 5MB");
        }
        String fileExtension = FilenameUtils.getExtension(imageName.getOriginalFilename());
        String generatedFileName = UUID.randomUUID().toString().replace("-", "");
        generatedFileName = generatedFileName + "." + fileExtension;
        Path destinationFilePath = this.storageFolder.resolve(
                        Paths.get(generatedFileName))
                .normalize().toAbsolutePath();
        if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
            throw new CustomException(403, "Không thể lưu trữ tệp bên ngoài thư mục hiện tại.");
        }
        try (InputStream inputStream = imageName.getInputStream()) {
            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://localhost:8080/v1/FileUpload/files/"+generatedFileName;
    }

    public String setImages(MultipartFile[] images) throws CustomException {
        List<String> list = new ArrayList<>();
        Arrays.asList(images).forEach(file -> {
            String fileExtension1 = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName1 = UUID.randomUUID().toString().replace("-", "");
            generatedFileName1 = generatedFileName1+"."+fileExtension1;
            Path destinationFilePath1 = this.storageFolder.resolve(
                            Paths.get(generatedFileName1))
                    .normalize().toAbsolutePath();
            if (!destinationFilePath1.getParent().equals(this.storageFolder.toAbsolutePath())) {
                throw new RuntimeException(
                        "Không thể lưu trữ tệp bên ngoài thư mục hiện tại.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath1, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add("http://localhost:8080/v1/FileUpload/files/"+generatedFileName1);
        });
        return list.toString();
    }
}
