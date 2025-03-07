package vn.fpoly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpoly.asm.service.sanPhamService;

@Controller
public class sanPhamController {
    @RequestMapping("/sanPham")
    public String sanPham() {
        return "page/SanPham";
    }
}
