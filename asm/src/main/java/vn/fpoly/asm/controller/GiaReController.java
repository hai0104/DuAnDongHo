package vn.fpoly.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GiaReController {
    @RequestMapping("/giaRe")
    public String giaRe() {
        return "page/GiaRe";
    }
}
