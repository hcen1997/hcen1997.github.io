package xyz.hcen.sblogserver.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.hcen.sblogserver.storage.StorageFileNotFoundException;
import xyz.hcen.sblogserver.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/file-download")
public class FileDownloadController {
    private StorageService storageService;

    @Autowired
    public FileDownloadController(StorageService storageService) {
        this.storageService = storageService;
    }

    // 文件下载api导航
    @GetMapping("")
    public Map root() {
        String info = "这是file-download模块,可用功能详见apis数组";
        String[] apis = {
                "/test-connect",
                "/list",
                "/download/{filename:.+}"
        };
        Map infos = new HashMap<String, Object>();
        infos.put("info", info);
        infos.put("apis", apis);
        return infos;
    }

    @GetMapping("/")
    public String rootRedirct(RedirectAttributes redirectAttributes) {
        // 不会写。怎么重定向？
        return "redirect:";
    }

    @GetMapping(value = "/test-connect")
    public String testConnect(HttpServletRequest request) {
        String info = "you're connecting " +
                request.getRequestURI();
        if (request.getQueryString() != null) {
            info += "?" + request.getQueryString();
        }
        return info;
    }

    @GetMapping(value = "/list")
    public List<String> listUploadedFiles(Model model) throws IOException {

        List<String> files = new ArrayList<>();
        files = storageService.loadAll().map(path -> MvcUriComponentsBuilder.fromMethodName(FileDownloadController.class,
                "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList());
//        List<Path> stream = storageService.loadAll().collect(Collectors.toList());
//        List<FileAndPath> fileList = new ArrayList<>();
//        for (Path path : stream) {
//            FileAndPath file = new FileAndPath();
//            file.setFileName(path.getFileName().toString());
//            file.setFileDownloadPath(MvcUriComponentsBuilder.fromMethodName(FileDownloadController.class,
//                    "serveFile", path.getFileName().toString()).build().toString());
//            fileList.add(file);
//        }
//        StringWriter stringWriter =new StringWriter() ;
//        new ObjectMapper().writeValue(stringWriter,fileList);
//
        // 如何把这个数组转换成json是一个问题。之后在解决 现在的解决方法是输出数组 在前端解析
        return files;
    }

    @GetMapping(value = "/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
