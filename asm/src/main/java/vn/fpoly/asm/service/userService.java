package vn.fpoly.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.model.users;
import vn.fpoly.asm.repository.userRepository;

import java.util.List;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;

    public userService(userRepository userRepo){
        this.userRepo = userRepo;
    }
    public List<users> getAllUsers(){
        return userRepo.findAll();
    }
    public void saveUsers(users users){
        userRepo.save(users);
    }
    public users getUserById(int id){
        return userRepo.findById(id).orElseGet(()->null);
    }
    public void deleteUser(int id){
        userRepo.deleteById(id);
    }

    public users login(String userName, String password) {
        users user = userRepo.findByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Đăng nhập thành công
        }
        return null; // Đăng nhập thất bại
    }

    public List<users> searchUserName(String userName) {
        return userRepo.findUserNameLike(userName);
    }
}
