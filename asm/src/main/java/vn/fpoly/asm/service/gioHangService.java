package vn.fpoly.asm.service;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;
        import vn.fpoly.asm.model.gioHangs;
        import vn.fpoly.asm.repository.gioHangRepository;

        import java.util.List;

@Service
public class gioHangService {
    @Autowired
    private gioHangRepository gioHangRepository;

    public gioHangService(gioHangRepository gioHangRepository) {
        this.gioHangRepository = gioHangRepository;
    }
    public List<gioHangs> getAllGioHangs() {
        return gioHangRepository.findAll();
    }
    public void saveGH(gioHangs gioHangs) {
        gioHangRepository.save(gioHangs);
    }

    public gioHangs getGioHangById(int id) {
        return gioHangRepository.findById(id).orElseGet(() -> null);
    }

    public void deleteGHangById(int id) {
        gioHangRepository.deleteById(id);
    }
}
