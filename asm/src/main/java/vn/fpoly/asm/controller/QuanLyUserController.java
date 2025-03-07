package vn.fpoly.asm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.model.users;
import vn.fpoly.asm.service.userService;

import java.util.List;

@Controller
public class QuanLyUserController {
    @Autowired
    private userService Userservice;

    @GetMapping("/user/list")
    public String getUserList(Model model) {
        List<users> userList = Userservice.getAllUsers();
        model.addAttribute("userLists", userList);
        model.addAttribute("user", new users());
        return "page/userList";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") users user,  RedirectAttributes redirectAttributes) {
        Userservice.saveUsers(user);
        redirectAttributes.addFlashAttribute("message","Thêm thành công!");
        return "redirect:/user/list";
    }
    @GetMapping("/editUser/{id}")
    public String getEditUser(@PathVariable("id") int id, Model model) {
        users user = Userservice.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("userLists", Userservice.getAllUsers());
        return "page/userList";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        users user = Userservice.getUserById(id);
        if(user != null) {
            Userservice.deleteUser(id);
            redirectAttributes.addFlashAttribute("message","Xóa thành công!");
        }else{
            redirectAttributes.addFlashAttribute("message","Xóa thất bại!");
        }
        return "redirect:/user/list";
    }

    @GetMapping("/admin")
    public String getAdmin(HttpSession session, Model model) {
        users user = (users) session.getAttribute("user");
        if (user == null || !user.getVaiTro()) { // Nếu không phải admin, chuyển về trang đăng nhập
            return "redirect:/";
        }
        return "ADMIN/HomeAdmin"; // Trả về trang quản trị
    }
    @RequestMapping("/searchUsername")
    public String searchUsername(@RequestParam("userName") String userName, Model model, RedirectAttributes redirectAttributes) {
        List<users> searchUsername = Userservice.searchUserName(userName);
        model.addAttribute("userLists", searchUsername);
        model.addAttribute("user", new users());
        if (searchUsername.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Tìm kiếm thành công!");
        } else {
            redirectAttributes.addFlashAttribute("message", "Tìm kiếm thất bại!");
        }
        return "page/userList";
    }
}
