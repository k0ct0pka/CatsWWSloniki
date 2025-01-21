package services.dtoServices;

import dao.impls.CatDao;
import dto.CatDto;
import dto.UserDto;
import factories.impls.CatFactory;
import filters.wrappers.FileDiskRequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.SneakyThrows;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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

        Integer id = Integer.valueOf(IOUtils.toString(req.getPart("id").getInputStream()));
        InputStream inputStream = req.getPart("image").getInputStream();
        if(inputStream.available()>0) {
            storageService.uploadFile(id, inputStream);
        }
        Map<String, String> params = new HashMap<>();
        params.put("name", IOUtils.toString(req.getPart("name").getInputStream()));
        params.put("breed", IOUtils.toString(req.getPart("breed").getInputStream()));
        CatDto build = catFactory.build(params);
        build.setUser((UserDto) req.getSession().getAttribute("user"));
        catDao.update(build);
    }

    @SneakyThrows
    @Override
    public void save(HttpServletRequest req) {
        InputStream inputStream = req.getPart("image").getInputStream();
        Map<String, String> params = new HashMap<>();
        params.put("name", IOUtils.toString(req.getPart("name").getInputStream()));
        params.put("breed", IOUtils.toString(req.getPart("breed").getInputStream()));

        log.info(params.toString());
        CatDto build = catFactory.build(params);
        build.setUser((UserDto) req.getSession().getAttribute("user"));
        Integer id = catDao.save(build);
        storageService.uploadFile(id, inputStream);
    }

    public List<CatDto> getCatsByUserId(Integer userId) {
        List<CatDto> all = catDao.findByUserId(userId);
        all.forEach(x -> {
            x.setImage(Base64.getEncoder().encodeToString(storageService.downloadFile(x.getId())));
        });
        return all;
    }
}
