package services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;

@RequiredArgsConstructor
@AllArgsConstructor
public class S3StorageService {
    private final String BUCKET_NAME = "catswwsloniki";
    private AmazonS3 s3;
    private Logger log;
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
        return objectContent.readAllBytes();
    }
}
