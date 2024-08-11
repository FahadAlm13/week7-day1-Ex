package spring.boot.week7day1ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.week7day1ex.Model.Merchant;
@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
}
