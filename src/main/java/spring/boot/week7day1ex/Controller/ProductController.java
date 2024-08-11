package spring.boot.week7day1ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day1ex.Model.Product;
import spring.boot.week7day1ex.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getAllProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProducts(product);
        return ResponseEntity.status(200).body("Product added successfully");
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@Valid @RequestBody Product product, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (productService.updateProduct(product, id)) {
            return ResponseEntity.status(200).body("Product updated successfully");
        } else return ResponseEntity.status(404).body("Product not found");

    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.status(200).body("Product deleted successfully");
        } else return ResponseEntity.status(404).body("Product not found");
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity getProductByName(@PathVariable String name) {
        if (productService.getProductByName(name) != null) {
            return ResponseEntity.status(200).body(productService.getProductByName(name));
        }
        return ResponseEntity.status(404).body("Product not found");
    }

    @GetMapping("/getProductByPrice")
    public ResponseEntity getProductByPrice() {
        List<Product> products = productService.getProductSortedByPrice();
        if (!products.isEmpty()) {
            return ResponseEntity.status(200).body(products);
        }
        return ResponseEntity.status(404).body("No products found in the store");
    }
}


