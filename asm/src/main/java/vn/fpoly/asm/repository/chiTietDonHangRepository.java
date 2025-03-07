package vn.fpoly.asm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.model.sanPhams;

import java.util.List;
@Repository
public interface chiTietDonHangRepository extends JpaRepository<chiTietDonHangs, Integer> {
    @Query("FROM chiTietDonHangs ctdh WHERE ctdh.maDHCT LIKE %:maDHCT%")
    List<chiTietDonHangs> findDHCTByMaDHLike(String maDHCT);



}
