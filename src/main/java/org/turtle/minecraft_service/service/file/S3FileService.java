package org.turtle.minecraft_service.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.turtle.minecraft_service.constant.InternalErrorType;
import org.turtle.minecraft_service.exception.InternalErrorException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
@Profile("prod-profile")
public class S3FileService implements FileService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    private final FileUtilService fileUtilService;


    @Override
    public String saveImageFile(MultipartFile file) throws InternalErrorException {
        fileUtilService.checkImageFile(file);
        String fileExtension = fileUtilService.getFileExtension(file);

        try {
            return uploadFileToS3("image/" + fileExtension, "images/", file);
        } catch (IOException e) {
            throw new InternalErrorException(InternalErrorType.FileSaveError);
        }
    }

    @Override
    public void deleteImageFiles(List<String> imageFilesNames) throws InternalErrorException {
        for (String imageFilesName : imageFilesNames) {
            deleteFileFromS3(imageFilesName);
        }
    }

    private String uploadFileToS3(String contentType, String filePath, MultipartFile file) throws InternalErrorException, IOException {
        String uniqueFilenameWithoutPath = fileUtilService.generateUniqueFileName(file);
        String uniqueFilename = filePath + uniqueFilenameWithoutPath;

        InputStream is = file.getInputStream();
        byte[] bytes = IOUtils.toByteArray(is); // MultipartFile을 byte[]로 변환

        ObjectMetadata metadata = new ObjectMetadata(); //metadata 생성
        metadata.setContentType(contentType);
        metadata.setContentLength(bytes.length);

        //S3에 요청할 때 사용할 byteInputStream 생성
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            // S3로 putObject 할 때 사용할 요청 객체 생성
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucket, uniqueFilename, byteArrayInputStream, metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);

            // S3에 파일 업로드
            s3Client.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new InternalErrorException(InternalErrorType.FileSaveError);
        } finally {
            byteArrayInputStream.close();
            is.close();
        }

        return uniqueFilenameWithoutPath;
    }

    private void deleteFileFromS3(String fileName) throws  InternalErrorException {
        try {
            s3Client.deleteObject(bucket, "images/" + fileName);
        }catch (Exception e){
            throw new InternalErrorException(InternalErrorType.FileDeleteError);
        }
    }
}
