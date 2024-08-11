package spring.boot.week7day1ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Product name must not be empty")
    @Size(min = 3,max = 30, message = "Product name  have to be more than 3 length long")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotNull(message = "Price must be not empty")
    @Positive
    @Column(columnDefinition = "double not null")
    private double price;

    @NotNull(message = "category id must be not empty ")
    @Column(columnDefinition = "int not null")
    private int categoryID;
}
