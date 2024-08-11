package spring.boot.week7day1ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day1ex.Model.Category;
import spring.boot.week7day1ex.Service.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body("Failed add category: " + message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("Category added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body("Failed update category: " + message);
        }
        if (categoryService.updateCategory(category, id)) {
            return ResponseEntity.status(200).body("Category updated successfully");
        } else return ResponseEntity.status(404).body("Category not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        if (categoryService.deleteCategory(id)) {
            return ResponseEntity.status(200).body("Category deleted successfully");
        } else return ResponseEntity.status(404).body("Category not found");
    }
}