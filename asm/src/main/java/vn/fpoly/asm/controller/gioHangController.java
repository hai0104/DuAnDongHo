package vn.fpoly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.gioHangs;
import vn.fpoly.asm.service.gioHangService;
import vn.fpoly.asm.service.sanPhamService;
import vn.fpoly.asm.service.userService;

import java.util.List;

@Controller
public class gioHangController {
@Autowired
    private gioHangService GHservice;
@Autowired
private sanPhamService SPService;
@Autowired
private userService userService;

@GetMapping("/gioHangList")
    public String gioHangList(Model model, @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
    List<gioHangs> listGH = GHservice.getAllGioHangs();
    model.addAttribute("listGH", listGH);
    model.addAttribute("listSP", SPService.getAllSanPham(page, size));
    model.addAttribute("listUser", userService.getAllUsers());
    model.addAttribute("gioHangs", new gioHangs());
    return "page/GioHang";
}

@PostMapping("/saveGH")
    public String saveGH(@RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size,@ModelAttribute("gh") gioHangs gioHang, Model model, RedirectAttributes redirectAttributes) {
    GHservice.saveGH(gioHang);
    model.addAttribute("listSP", SPService.getAllSanPham(page, size));
    model.addAttribute("listUser", userService.getAllUsers());
    redirectAttributes.addFlashAttribute("message","Thêm vào giỏ hàng thành công!");
    return "redirect:/gioHangList";
}

@GetMapping("/deleteGH/{id}")
    public String deleteGH(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
    gioHangs gioHang = GHservice.getGioHangById(id);
    if (gioHang != null) {
        GHservice.deleteGHangById(id);
    }
    redirectAttributes.addFlashAttribute("message", "Xóa thành công!");
    return "redirect:/gioHangList";
}




}
