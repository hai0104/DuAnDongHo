package vn.fpoly.asm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpoly.asm.model.chiTietDonHangs;
import vn.fpoly.asm.repository.chiTietDonHangRepository;

import java.util.List;

@Service
public class QuanLyDonHangService {
    @Autowired
    private chiTietDonHangRepository CTDHRepo;

    public QuanLyDonHangService(chiTietDonHangRepository CTDHRepo) {
        this.CTDHRepo = CTDHRepo;
    }

    public List<chiTietDonHangs> getAllCTDH() {
        return CTDHRepo.findAll();
    }

    public void saveDonHangs(chiTietDonHangs donHangs) {
        CTDHRepo.save(donHangs);
    }
    public chiTietDonHangs getCTDHById(int id) {
        return CTDHRepo.findById(id).orElseGet(() -> null);
    }

    public void deleteDonHangChiTiet(chiTietDonHangs donHangs) {
        CTDHRepo.delete(donHangs);
    }

    public List<chiTietDonHangs> searchMaDH(String maDHCT){
        return CTDHRepo.findDHCTByMaDHLike(maDHCT);
    }

}
