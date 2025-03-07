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
@Table(name = "loai_hang")
public class loaiSP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idLoai;
    @Column(name = "tenLoaiSP")
    private String tenLoai;
    @Column(name = "moTa")
    private String moTa;
}
