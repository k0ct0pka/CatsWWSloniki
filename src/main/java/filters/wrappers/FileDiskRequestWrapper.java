package filters.wrappers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.fileupload2.core.DiskFileItem;

import java.util.*;
import java.io.IOException;

public class FileDiskRequestWrapper extends HttpServletRequestWrapper {
    private List<DiskFileItem> fileItems;
    public FileDiskRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    public List<DiskFileItem> getFileDiskItems() throws IOException {
        return fileItems;
    }
    public void setFileDiskItems(List<DiskFileItem> fileItems) {
        this.fileItems = fileItems;
    }
}
