package gov.gz.hydrology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年09月04日
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("")
    public String index() {
        return "views/index";
    }

    @GetMapping("views/{folder}/{file}")
    public String view(@PathVariable("folder") String folder, @PathVariable("file") String file) {
        return "views/" + folder + "/" + file;
    }


}
