package vn.fpoly.asm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.model.donHangs;
import vn.fpoly.asm.service.QuanLyDonHangService;
import vn.fpoly.asm.service.donHangService;
import vn.fpoly.asm.service.sanPhamService;

import java.util.List;

@Controller
public class quanLyDonHang {
    @Autowired
    private QuanLyDonHangService QLDHService;
    @Autowired
    private sanPhamService SPService;
    @Autowired
    private donHangService DHService;

    @GetMapping("/quanLyDH/list")
    public String getListQLDH(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        List<chiTietDonHangs> listQLDH = QLDHService.getAllCTDH();
        model.addAttribute("listQLDH", listQLDH);
        model.addAttribute("listSP", SPService.getAllSanPham(page, size));
        model.addAttribute("listDH", DHService.getAllDonHang());
        model.addAttribute("quanLyDH", new chiTietDonHangs());
        return "page/QuanLyDonHang";
    }

    @GetMapping("/quanLyDH/user/list")
    public String getListQLDHUser(Model model, @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size) {
        List<chiTietDonHangs> listQLDH = QLDHService.getAllCTDH();
        model.addAttribute("listQLDH", listQLDH);
        model.addAttribute("listSP", SPService.getAllSanPham(page, size));
        model.addAttribute("listDH", DHService.getAllDonHang());
        model.addAttribute("quanLyDH", new chiTietDonHangs());
        return "page/AddDonHang";
    }

    @PostMapping("quanLyDH/user/saveQLDH")
    public String saveQLDonHang(@ModelAttribute("CTDH") chiTietDonHangs ctDonHang,
                                Model model, RedirectAttributes redirectAttributes,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        QLDHService.saveDonHangs(ctDonHang);
        model.addAttribute("listSP", SPService.getAllSanPham(page, size));
        model.addAttribute("listDH", DHService.getAllDonHang());
        redirectAttributes.addFlashAttribute("message", "Mua hàng thành công!");
        return "redirect:/quanLyDH/user/list";
    }

    @GetMapping("/deleteDHCT/{id}")
    public String deleteDHCT(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        chiTietDonHangs CTDH = QLDHService.getCTDHById(id);
        if (CTDH != null) {
            QLDHService.deleteDonHangChiTiet(CTDH);
            redirectAttributes.addFlashAttribute("message", "Xoá thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Xoá thất bại!");
        }
        return "redirect:/quanLyDH/list";
    }

    @RequestMapping("/searchMaCTDH")
    public String searchMaDHCT(@RequestParam("maDHCT") String maDHCT, Model model, RedirectAttributes redirectAttributes) {
        List<chiTietDonHangs> searchMa = QLDHService.searchMaDH(maDHCT);
        model.addAttribute("listQLDH", searchMa);
        if (searchMa.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Tìm kiếm thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Tìm kiếm thất bại!");
        }
        return "page/QuanLyDonHang";
    }
}
