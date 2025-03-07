package vn.fpoly.asm.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.sanPhams;
import vn.fpoly.asm.service.loaiSPService;
import vn.fpoly.asm.service.sanPhamService;

import java.util.List;

@Controller
public class danhSachSanPhamController {
    @Autowired
    private sanPhamService serviceSP;
    @Autowired
    private loaiSPService loaiSPService;

    @GetMapping("/sanPham/list")
    public String getSanPham(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        Page<sanPhams> listSP = serviceSP.getAllSanPham(page, size);
        model.addAttribute("list", listSP);
        model.addAttribute("trangHienTai", page);
        model.addAttribute("tongSoTrang", listSP.getTotalElements());
        model.addAttribute("loaisp", loaiSPService.getAllLoaiSP());
        model.addAttribute("sanPhams", new sanPhams());
        return "page/SanPhamList";
    }

    @PostMapping("/save")
    public String saveSanPham(@ModelAttribute("sp") sanPhams sanpham,
                              @RequestParam("file") MultipartFile file, Model model,
                              RedirectAttributes redirectAttributes) {
        serviceSP.saveSanPham(sanpham, file);
        redirectAttributes.addFlashAttribute("message", "Thêm thành công!");
        return "redirect:/sanPham/list";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable("id") int id, Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {
        sanPhams sp = serviceSP.getSanPhamById(id);
        model.addAttribute("list", serviceSP.getAllSanPham(page, size));
        Page<sanPhams> listSP = serviceSP.getAllSanPham(page, size);
        model.addAttribute("trangHienTai", page);
        model.addAttribute("tongSoTrang", listSP.getTotalElements());
        model.addAttribute("loaisp", loaiSPService.getAllLoaiSP());
        model.addAttribute("sanPhams", sp);
        return "page/SanPhamList";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        sanPhams sp = serviceSP.getSanPhamById(id);
        if(sp != null) {
            serviceSP.removeSP(sp.getId());
            redirectAttributes.addFlashAttribute("message", "Xoá thành công!");
        }else{
            redirectAttributes.addFlashAttribute("message", "Xoá thất bại!");
        }

        return "redirect:/sanPham/list";
    }

    @GetMapping("/searchTenSP")
    public String searchTenSP(@RequestParam("tenSP") String tenSP, Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size,
                              RedirectAttributes redirectAttributes) {
        Page<sanPhams> searchName = serviceSP.searchTenSP(tenSP, page, size);
        // Nếu không tìm thấy sản phẩm, vẫn hiển thị trang với thông báo
        if (searchName.isEmpty()) {
            model.addAttribute("message", "Không tìm thấy sản phẩm nào!");
        }
        model.addAttribute("list", searchName);
        model.addAttribute("trangHienTai", page);
        model.addAttribute("tongSoTrang", searchName.getTotalPages());
        model.addAttribute("loaisp", loaiSPService.getAllLoaiSP());
        model.addAttribute("sanPhams", new sanPhams());
        redirectAttributes.addFlashAttribute("message", "Tìm kiếm thành công!");
        return "page/SanPhamList";
    }



}
