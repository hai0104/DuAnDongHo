package vn.fpoly.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpoly.asm.model.donHangs;
import vn.fpoly.asm.repository.donHangRepository;

import java.util.List;

@Service
public class donHangService {
@Autowired
    private donHangRepository DHRepo;

public donHangService(donHangRepository DHRepo) {
    this.DHRepo = DHRepo;
}

public List<donHangs> getAllDonHang(){
    return DHRepo.findAll();
}
public void saveDH(donHangs donHangs) {
    DHRepo.save(donHangs);
}
}
