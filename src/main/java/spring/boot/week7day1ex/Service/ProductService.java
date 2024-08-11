package spring.boot.week7day1ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day1ex.Model.Product;
import spring.boot.week7day1ex.Repository.ProductRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
   private final ProductRepository productRepository;
    //Get
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    //Post
    public void addProducts(Product products) {
        productRepository.save(products);
    }

    //update
    public boolean updateProduct(Product product, Integer id) {
        Product p = productRepository.getById(id);
        if (p == null) {
            return false;
        }
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategoryID(product.getCategoryID());

        productRepository.save(p);
        return true;
    }

    //delete
    public boolean deleteProduct(Integer id) {
        Product p = productRepository.getById(id);
        if (p == null) {
            return false;
        }
        productRepository.delete(p);
        return true;
    }

    //search for name of product | extra 1
    public Product getProductByName(String name) {
        for (Product product : productRepository.findAll()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
    //extra 2
    public List<Product> getProductSortedByPrice() {
        List<Product> productSorted = productRepository.findAll();
        productSorted.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        return productSorted;
    }


    public Product getProductById(Integer id) {
        for (Product product : productRepository.findAll()) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}

