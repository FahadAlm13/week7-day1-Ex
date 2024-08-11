package spring.boot.week7day1ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "User name must be not empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String username;

    @NotEmpty(message = "password must be not empty")
    @Size(min = 6,message = "password must have to be more than 6 length long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least 6 characters, including both characters and digits")
//    @Column(columnDefinition = "varchar(30) check(password REGEXP '^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{6,}$') not null") جربت كل شيء مب راضي يضبط
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @NotEmpty(message = "Email must be not empty")
    @Email
    @Column(columnDefinition = "varchar(40) not null")
    private String email;

    @NotEmpty(message = "Role must be not empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
//    @Column(columnDefinition = "varchar(10) CHECK (role IN ('Admin', 'Customer'))") نفس الشي مب راضي يضبط
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = "Balance must not be empty")
    @Positive(message = "Balance must be positive")
    @Column(columnDefinition = "double not null")
    private double balance;

//    private ArrayList<Integer> wishlist = new ArrayList<>();


    @Column(columnDefinition = "boolean")
    private boolean isPrime = false ;
}
