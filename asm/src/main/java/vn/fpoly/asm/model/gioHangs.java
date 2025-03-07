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
@Table(name = "gio_hang")
public class gioHangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "maSP_id") // Chỉ cần name, không cần referencedColumnName
    private sanPhams sanpham;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private users user;

    @Column(name = "soLuong")
    private  Integer soLuong;
}

