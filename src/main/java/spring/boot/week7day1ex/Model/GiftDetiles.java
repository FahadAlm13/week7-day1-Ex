package spring.boot.week7day1ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "GiftDetiles")
public class GiftDetiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull(message = "product id should be not empty")
    @Column(columnDefinition = "int not null")
    private int productId;

    @Positive
    @Column(columnDefinition = "double not null")
    private double price;

    @Size(max = 50,message = "note must be not largest than 50 characters")
    @Column(columnDefinition = "varchar(50)")
    private String note;
}
