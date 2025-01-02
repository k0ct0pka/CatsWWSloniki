package services.dtoServices;

import dao.impls.CatDao;
import dto.CatDto;
import dto.UserDto;
import factories.impls.CatFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.BaseDtoService;
import services.StorageService;

import java.io.InputStream;
import java.net.http.HttpRequest;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatService implements BaseDtoService<CatDto> {
    private static final Logger log = LoggerFactory.getLogger(CatService.class);
    @Autowired
    private CatDao catDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private CatFactory catFactory;
    @Autowired
    private JakartaServletDiskFileUpload diskFileItemFactory;
    public List<CatDto> getAll(){
        List<CatDto> all = catDao.findAll();
        all.forEach(x -> {
            x.setImage(Base64.getEncoder().encodeToString(storageService.downloadFile(x.getId())));
        });
        return all;
    }

    @Override
    public void delete(Integer id) {
        catDao.delete(id);
    }
    @SneakyThrows
    @Override
    public void update(HttpServletRequest req) {
        List<DiskFileItem> diskFileItems = diskFileItemFactory.parseRequest(req);
        Integer id = Integer.valueOf(diskFileItems.get(1).getString());
        InputStream inputStream = diskFileItems.get(2).getInputStream();
        if(inputStream.available()>0) {
            storageService.uploadFile(id, inputStream);
        }
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i<diskFileItems.size(); i++) {
            if(i==2)continue;
            params.put(diskFileItems.get(i).getFieldName(), diskFileItems.get(i).getString());
        }
        CatDto build = catFactory.build(params);
        build.setUser((UserDto) req.getSession().getAttribute("user"));
        catDao.update(build);
    }

    @SneakyThrows
    @Override
    public void save(HttpServletRequest req) {
        List<DiskFileItem> diskFileItems = diskFileItemFactory.parseRequest(req);
        DiskFileItem diskFileItem = diskFileItems.get(2);
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i<diskFileItems.size() - 1; i++) {
            params.put(diskFileItems.get(i).getFieldName(), diskFileItems.get(i).getString());
        }

        log.info(params.toString());
        CatDto build = catFactory.build(params);
        build.setUser((UserDto) req.getSession().getAttribute("user"));
        Integer id = catDao.save(build);
        storageService.uploadFile(id, diskFileItem.getInputStream());
    }

    public List<CatDto> getCatsByUserId(Integer userId) {
        List<CatDto> all = catDao.findByUserId(userId);
        all.forEach(x -> {
            x.setImage(Base64.getEncoder().encodeToString(storageService.downloadFile(x.getId())));
        });
        return all;
    }
}
