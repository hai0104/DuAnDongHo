package vn.fpoly.asm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "san_pham")
public class sanPhams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "maSP", nullable = false, unique = true)
    private String maSP;

    @Column(name = "tenSP", nullable = false)
    private String tenSP;

    @Column(name = "gia", nullable = false)
    private double gia;

    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    @Column(name = "anhSP")
    private String anhSP;

    @Column(name = "moTa")
    private String moTa;

    @Column(name = "xuatSu")
    private String xuatSu;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private loaiSP category;
}
