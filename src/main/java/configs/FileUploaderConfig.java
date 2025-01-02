package configs;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploaderConfig {
    @Bean
    public JakartaServletDiskFileUpload diskFileItemFactory() {
        return new JakartaServletDiskFileUpload(new DiskFileItemFactory.Builder()
                .setFileCleaningTracker(new FileCleaningTracker())
                .setBufferSize(DiskFileItemFactory.DEFAULT_THRESHOLD)
                .get());
    }
}
