package spring.boot.week7day1ex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MerchantStock")
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product id must be not empty")
    @Column(columnDefinition = "int not null")
    private int productId;

    @NotNull(message = "Merchant id must be not empty")
    @Column(columnDefinition = "int not null")
    private int merchantId;

    @NotNull(message = "Stock must be not empty")
    @Column(columnDefinition = "int not null default 10")
    private int stock = 10;
}
