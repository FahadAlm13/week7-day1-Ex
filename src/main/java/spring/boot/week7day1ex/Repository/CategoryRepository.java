package spring.boot.week7day1ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.week7day1ex.Model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
