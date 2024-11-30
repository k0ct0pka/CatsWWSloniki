package services;

import java.io.InputStream;

public interface StorageService {
    void uploadFile(Integer id, InputStream inputStream);
    byte[] downloadFile(Integer id);
}
