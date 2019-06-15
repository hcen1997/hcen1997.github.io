package xyz.hcen.sblogserver.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.hcen.sblogserver.storage.StorageException;
import xyz.hcen.sblogserver.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

// 文件上传模块 功能只有上传文件 不包括显示文件列表
@CrossOrigin("*")
@RestController
@RequestMapping("/api/file-upload")
public class FileUploadController {
    private StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    // 文件上传导航
    @GetMapping("")
    public Map root() {
        String info = "这是file-upload模块,可用功能详见apis数组";
        String[] apis = {
                "/test-connect",
                "/upload"
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

    @PostMapping(value = "/upload")
    public Map handleFileUpload(@RequestParam(name = "file") MultipartFile file) {
        Map<String,String> result = new HashMap<>();
        try {
            storageService.store(file);
        } catch (StorageException e) {
            result.put("state","error");
            result.put("message",e.getMessage());
            return result;
        }
        result.put("state","success");
        result.put("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return result;
    }

}
