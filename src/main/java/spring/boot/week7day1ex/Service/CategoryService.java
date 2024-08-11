package spring.boot.week7day1ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day1ex.Model.Category;
import spring.boot.week7day1ex.Repository.CategoryRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public boolean updateCategory(Category category, Integer id) {
        Category c =categoryRepository.getById(id);
        if (c == null) {
            return false;
        }
        c.setName(category.getName());
        categoryRepository.save(c);
        return true;
    }
    public boolean deleteCategory(Integer id) {
        Category category = categoryRepository.getById(id);
        if (category == null) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

}
