package vn.fpoly.asm.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "hoTen")
    private String hoTen;
    @Column(name = "vaiTro")
    private Boolean vaiTro;
    @Column(name = "ngayTao")
    private Date ngayTao = new Date();
}
