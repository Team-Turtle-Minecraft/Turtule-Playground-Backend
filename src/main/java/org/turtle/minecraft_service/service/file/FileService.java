package org.turtle.minecraft_service.service.file;

import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.exception.InternalErrorException;

import java.util.List;

public interface FileService {

    String saveImageFile(MultipartFile file) throws InternalErrorException;

    void deleteImageFiles(List<String> imageFilesName) throws InternalErrorException;
}
