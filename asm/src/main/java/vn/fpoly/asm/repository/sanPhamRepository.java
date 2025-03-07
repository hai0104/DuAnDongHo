package vn.fpoly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.sanPhams;
@Repository
public interface sanPhamRepository extends JpaRepository<sanPhams, Integer> {
    Page<sanPhams> findAll(Pageable pageable);

    @Query("FROM sanPhams sp WHERE sp.tenSP LIKE %:tenSP%")
    Page<sanPhams> findSPByTenSPLike(String tenSP, Pageable pageable);
}
