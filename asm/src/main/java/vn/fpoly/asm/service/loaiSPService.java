package vn.fpoly.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpoly.asm.model.loaiSP;
import vn.fpoly.asm.repository.loaiSPrepository;

import java.util.List;

@Service
public class loaiSPService {
    @Autowired
    private loaiSPrepository loaiSPRepo;

    public loaiSPService(loaiSPrepository loaiSPRepo) {
        this.loaiSPRepo = loaiSPRepo;
    }
    public List<loaiSP> getAllLoaiSP() {
        return loaiSPRepo.findAll();
    }

    public void saveLoaiHH(loaiSP loaiSP) {
        loaiSPRepo.save(loaiSP);
    }

    public loaiSP getLoaiSPById(int id) {
        return loaiSPRepo.findById(id).orElseGet(() -> null);
    }

    public void deleteLoaiSPById(int id) {
        loaiSPRepo.deleteById(id);
    }
}
