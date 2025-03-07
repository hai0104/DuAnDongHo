package vn.fpoly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.model.users;

import java.util.List;
@Repository
public interface userRepository extends JpaRepository<users, Integer> {
    users findByUserName(String userName); // Tìm user theo tên đăng nhập
    @Query("FROM users u WHERE u.userName LIKE %:userName%")
    List<users> findUserNameLike(String userName);
}
