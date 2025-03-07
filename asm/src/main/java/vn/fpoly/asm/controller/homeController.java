package vn.fpoly.asm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
    @RequestMapping("/homeSivar")
    public String home() {
        return "Home";
    }
}
