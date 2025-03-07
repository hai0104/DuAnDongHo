package vn.fpoly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.gioHangs;
@Repository
public interface gioHangRepository extends JpaRepository<gioHangs, Integer> {
}
