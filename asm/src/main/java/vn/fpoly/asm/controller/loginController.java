package vn.fpoly.asm.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.fpoly.asm.model.users;
import vn.fpoly.asm.service.userService;

@Controller
public class loginController {
    @Autowired
    private userService userservice;
    @GetMapping("/")
    public String loginPage() {
        return "page/DangNhap";  // Chuyển đến trang dangNhap.html
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpSession session, Model model) {
        users user = userservice.login(userName, password);
        if (user != null) {
            session.setAttribute("user", user);
            if (user.getVaiTro()) { // Nếu vai trò là admin (true)
                return "ADMIN/HomeAdmin";
            } else { // Nếu là user (false)
                return "USER/HomeUser";
            }
        }
        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
        return "page/DangNhap";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
