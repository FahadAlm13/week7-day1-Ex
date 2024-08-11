package spring.boot.week7day1ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.week7day1ex.Model.GiftDetiles;

import java.util.List;

@Repository
public interface GiftDetilesRepository extends JpaRepository<GiftDetiles, Integer> {
    List<GiftDetiles> findByUserId(int userId);
}
