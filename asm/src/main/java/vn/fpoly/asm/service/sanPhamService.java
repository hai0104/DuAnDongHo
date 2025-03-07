package vn.fpoly.asm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fpoly.asm.model.sanPhams;
import vn.fpoly.asm.repository.sanPhamRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class sanPhamService {
    @Autowired
    private sanPhamRepository repoSP;
    private final String UPLOAD_DIR = "src/main/resources/static/image/";

    public sanPhamService(sanPhamRepository repoSP) {
        this.repoSP = repoSP;
    }

    public Page<sanPhams> getAllSanPham(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repoSP.findAll(pageable);
    }

    public Page<sanPhams> searchTenSP(String tenSP, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repoSP.findSPByTenSPLike("%"+tenSP+"%", pageable);
    }

    public sanPhams getSanPhamById(int id) {
        return repoSP.findById(id).orElseGet(()->null);
    }

    public void saveSanPham(sanPhams sanPham, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                Path uploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                sanPham.setAnhSP("/image/" + fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        repoSP.save(sanPham);
    }
    public void removeSP(int id) {
        repoSP.deleteById(id);
    }
}
