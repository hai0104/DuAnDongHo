package vn.fpoly.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class trangChuController {
    @RequestMapping("/trangChu")
    public String trangChu() {
        return "page/TrangChu";
    }
}
