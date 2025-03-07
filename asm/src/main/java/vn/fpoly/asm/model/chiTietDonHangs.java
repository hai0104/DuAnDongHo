package vn.fpoly.asm.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
@Table(name = "chi_tiet_don_hang")
public class chiTietDonHangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "maDHCT")
    private String maDHCT;
    @Column(name = "soLuong")
    private Integer soLuong;
    @Column(name = "donGia")
    private double donGia;

    @Column(name = "diaChi")
    private String diaChi;

    @ManyToOne
    @JoinColumn(name = "maSP_id") // Chỉ cần name, không cần referencedColumnName
    private sanPhams sanpham;

    @ManyToOne
    @JoinColumn(name = "donhangid")
    private donHangs donhang;
}
