package vn.fpoly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.loaiSP;
import vn.fpoly.asm.service.loaiSPService;

import java.util.List;

@Controller
public class LoaiHangHoaController {
    @Autowired
    private loaiSPService loaiSPService;

    @GetMapping("/loaiHangHoa/list")
    public String loaiHangHoa(Model model) {
        List<loaiSP> loaiList = loaiSPService.getAllLoaiSP();
        model.addAttribute("loaiList", loaiList);
        model.addAttribute("loaisp", new loaiSP());
        return "page/LoaiHangHoa";
    }

    @PostMapping("/saveLoaiHH")
    public String saveLoaiHH(@ModelAttribute("lhh") loaiSP loaisp, RedirectAttributes redirectAttributes) {
        loaiSPService.saveLoaiHH(loaisp);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/loaiHangHoa/list";
    }

    @GetMapping("/editLoaiHH/{id}")
    public String editLoaiHH(@PathVariable("id") int id, Model model) {
        loaiSP loaisp = loaiSPService.getLoaiSPById(id);
        model.addAttribute("loaisp", loaisp);
        model.addAttribute("loaiList", loaiSPService.getAllLoaiSP());

        return "page/LoaiHangHoa";
    }

    @GetMapping("/deleteLoaiHH/{id}")
    public String deleteLoaiHH(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        loaiSP loaisp = loaiSPService.getLoaiSPById(id);

        if(loaisp != null) {
            loaiSPService.deleteLoaiSPById(id);
            redirectAttributes.addFlashAttribute("message","Xóa thành công!");
        }else{
            redirectAttributes.addFlashAttribute("message","Xóa thất bại!");
        }

        return "redirect:/loaiHangHoa/list";
    }
}
