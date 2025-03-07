package vn.fpoly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.gioHangs;
@Repository
public interface gioHangRepository extends JpaRepository<gioHangs, Integer> {
    Page<gioHangs> findBySanphamTenSPContainingIgnoreCase(String keyword, Pageable pageable);
}
