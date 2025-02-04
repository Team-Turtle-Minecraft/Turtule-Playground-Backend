package org.turtle.minecraft_service.service.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.constant.InternalErrorType;
import org.turtle.minecraft_service.exception.InternalErrorException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileUtilService {

    protected void checkImageFile(MultipartFile imageFile) {

        checkFileValid(imageFile);

        if(!(imageFile.getContentType() != null && imageFile.getContentType().startsWith("image"))) {
            throw new InternalErrorException(InternalErrorType.EmptyFileError);
        }

        String extension = getFileExtension(imageFile);
        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png");
        if (!allowedExtentionList.contains(extension)) {
            throw new InternalErrorException(InternalErrorType.InvalidFileExtension);
        }
    }

    protected String saveFileInStorage(MultipartFile file, String uploadPath) throws InternalErrorException {
        try {
            String uniqueFileName = generateUniqueFileName(file);
            Path fullFilePath = Paths.get(uploadPath + "/" + uniqueFileName);
            Files.copy(file.getInputStream(), fullFilePath);

            return uniqueFileName;
        } catch (Exception e) {
            throw new InternalErrorException(InternalErrorType.FileSaveError);
        }
    }

    protected void deleteFileInStorage(String fileName, String uploadedPath) throws InternalErrorException {
        try {
            String fullFilePath = uploadedPath + "/" + fileName;
            File file = new File(fullFilePath);
            if(!file.delete()){
                throw new InternalErrorException(InternalErrorType.FileDeleteError);
            };
        } catch (Exception e) {
            throw new InternalErrorException(InternalErrorType.FileDeleteError);
        }
    }

    protected void checkFileValid(MultipartFile file) {
        if (file.isEmpty() || Objects.isNull(file.getOriginalFilename())) {
            throw new InternalErrorException(InternalErrorType.EmptyFileError);
        }
    }

    protected String getFileExtension(MultipartFile file) {
        String filename = file.getOriginalFilename();
        if (filename == null) {
            throw new InternalErrorException(InternalErrorType.NoFilenameError);
        }

        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            throw new InternalErrorException(InternalErrorType.NoFileExtension);
        }

        return filename.substring(lastDotIndex + 1).toLowerCase();
    }

    protected String generateUniqueFileName(MultipartFile file) {

        String fileExtension = getFileExtension(file);
        String uniqueFileName = UUID.randomUUID().toString().replaceAll("-", "");
        return uniqueFileName + "." + fileExtension;
    }
}
