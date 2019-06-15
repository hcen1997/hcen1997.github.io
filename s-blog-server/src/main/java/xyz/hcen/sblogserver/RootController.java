package xyz.hcen.sblogserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/api")
    public Map rootInfo() {
        Map<String, Object> infos = new HashMap<>();
        String info = "这是api的根目录";
        String usage = "可用的api";
        String[] apis = {"/file-upload",
                         "/file-download"};
        infos.put("info", info);
        infos.put("usage", usage);
        infos.put("apis", apis);
        return infos;
    }
}
