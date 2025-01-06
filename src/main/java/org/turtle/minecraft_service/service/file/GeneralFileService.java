package org.turtle.minecraft_service.service.file;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.exception.InternalErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Profile({"local-profile", "dev-profile"})
public class GeneralFileService implements FileService{

    @Value("${file.upload.image.path}")
    private String imageUploadPath;


    private final FileUtilService fileUtilService;


    @Override
    public String saveImageFile(MultipartFile file) throws InternalErrorException {
        fileUtilService.checkImageFile(file);
        return fileUtilService.saveFileInStorage(file, imageUploadPath);
    }

    @Override
    public void deleteImageFiles(List<String> savedImagesInProgress) throws InternalErrorException {
        if (savedImagesInProgress != null) {
            for (String imageFileName : savedImagesInProgress) {
                fileUtilService.deleteFileInStorage(imageFileName, imageUploadPath);
            }
        }
    }
}
