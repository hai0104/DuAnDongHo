package vn.fpoly.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class trungCapController {
    @RequestMapping("/trungCap")
    public String trungCap() {
        return "page/TrungCap";
    }
}
