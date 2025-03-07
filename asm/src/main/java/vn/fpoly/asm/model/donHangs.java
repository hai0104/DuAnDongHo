package vn.fpoly.asm.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
@Table(name = "don_hang")
public class donHangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idDH;
    @Column(name = "maDH")
    private String maDH;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;

    @Column(name = "ngay_dat_hang")
    private Date ngayDatHang = new Date();
    @Column(name = "soLuongDat")
    private Integer soLuongDat;
    @Column(name = "tongGia")
    private double tongGia;
    @Column(name = "trangThai")
    @Builder.Default
    private String trangThai = "Chờ xác nhận";

}
