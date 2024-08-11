package spring.boot.week7day1ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.week7day1ex.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
