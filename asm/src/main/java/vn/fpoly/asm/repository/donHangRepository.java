package vn.fpoly.asm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.donHangs;
@Repository
public interface donHangRepository extends JpaRepository<donHangs, Integer> {
}
