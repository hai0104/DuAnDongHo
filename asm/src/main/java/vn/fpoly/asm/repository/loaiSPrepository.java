package vn.fpoly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.loaiSP;
@Repository
public interface loaiSPrepository extends JpaRepository<loaiSP, Integer> {
}
