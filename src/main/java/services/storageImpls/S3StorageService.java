package services.storageImpls;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import dao.impls.UserDao;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import services.StorageService;

import java.io.InputStream;

@RequiredArgsConstructor
@AllArgsConstructor
@Service
@ComponentScan("src.main.java.configs")
public class S3StorageService implements StorageService {
    @Value("${AWS.S3.BUCKET_NAME}")
    private final String BUCKET_NAME;
    private final AmazonS3 s3;
    private Logger log  = LoggerFactory.getLogger(S3StorageService.class);
    @SneakyThrows
    public void uploadFile(Integer id, InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        String fileName = "/cats/" + id;
        s3.putObject(new PutObjectRequest(BUCKET_NAME,fileName,inputStream,objectMetadata));
        inputStream.close();
        log.info("Uploaded file{}",id);
    }
    @SneakyThrows
    public byte[] downloadFile(Integer id) {
        S3Object object = s3.getObject(new GetObjectRequest(BUCKET_NAME, "/cats/"+id));
        S3ObjectInputStream objectContent = object.getObjectContent();
        log.info("Downloaded file{}",id);
        return objectContent.readAllBytes();
    }
}
