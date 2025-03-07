package vn.fpoly.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.users;
import vn.fpoly.asm.service.userService;

import java.util.List;

@Controller
public class DangKyTaiKhoan {
    @Autowired
    private userService userService;

    @GetMapping("/dangKy")
    public String dangKyTaiKhoan(Model model) {
        List<users> userList = userService.getAllUsers();
        model.addAttribute("userLists", userList);
        model.addAttribute("user", new users());
        return "page/dangKyTaiKhoan";
    }

    @PostMapping("/saveTaiKhoan")
    public String saveUser(@ModelAttribute("user") users user, RedirectAttributes redirectAttributes) {
        userService.saveUsers(user);
        redirectAttributes.addFlashAttribute("message","Đăng ký thành công!");
        return "redirect:/";
    }
}
