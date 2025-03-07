package vn.fpoly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fpoly.asm.model.sanPhams;
import vn.fpoly.asm.service.sanPhamService;

@Controller
public class caoCapController {

    @RequestMapping("/caoCap")
    public String caoCap() {

        return "page/CaoCap";
    }
}
