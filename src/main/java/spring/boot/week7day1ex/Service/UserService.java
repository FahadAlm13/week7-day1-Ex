package spring.boot.week7day1ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day1ex.Model.GiftDetiles;
import spring.boot.week7day1ex.Model.MerchantStock;
import spring.boot.week7day1ex.Model.Product;
import spring.boot.week7day1ex.Model.User;
import spring.boot.week7day1ex.Repository.GiftDetilesRepository;
import spring.boot.week7day1ex.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final GiftDetilesRepository GiftDetilesRepository;
    private final GiftDetilesRepository giftDetilesRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user, Integer id) {
        User u = userRepository.getById(id);
        if (u == null) {
            return false;
        }
        u.setBalance(user.getBalance());
        u.setEmail(user.getEmail());
        u.setPrime(user.isPrime());
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        u.setRole(user.getRole());
        userRepository.save(u);
        return true;
    }

    public boolean deleteUser(Integer id) {
        User u = userRepository.getById(id);
        if (u == null) {
            return false;
        }
        userRepository.delete(u);
        return true;
    }


    // Buy product
    public String buyProduct(Integer userId, int productId, int merchantId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            System.out.println("User not found");
            return "User not found";
        }
        System.out.println("User found: " + user);

        if (!user.getRole().equalsIgnoreCase("Customer")) {
            return "Only customers can buy products";
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            System.out.println("Product not found in list: " + productId);
            return "Product not found";
        }
        System.out.println("Product found: " + product);

        MerchantStock merchantStock = merchantStockService.getMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            System.out.println("Merchant stock not found");
            return "Merchant stock not found";
        }
        System.out.println("Merchant stock found: " + merchantStock);

        if (merchantStock.getStock() <= 0) {
            System.out.println("Product out of stock");
            return "Product out of stock";
        }

        double price = user.isPrime() ? product.getPrice() * 0.90 : product.getPrice();
        if (user.getBalance() < price) {
            System.out.println("Insufficient balance");
            return "Insufficient balance";
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        merchantStockService.updateMerchantStock(merchantStock, merchantStock.getId());

        user.setBalance(user.getBalance() - price);
        userRepository.save(user);

        System.out.println("Product purchased successfully");
        return "Product purchased successfully" + (user.isPrime() ? " with 10% discount" : "");
    }
    public boolean subscribePrime(int userId) {
        double monthlyFee = 39.0;
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        if (user.getBalance() < monthlyFee) {
            return false;
        }
        user.setBalance(user.getBalance() - monthlyFee);
        user.setPrime(true);
        userRepository.save(user);
        return true;
    }

    // If you gift someone, you can use this method to see the gift details
    public List<GiftDetiles> getGiftByUserDetails(int userId) {
        return giftDetilesRepository.findByUserId(userId);
    }

    // تقدر تهدي احد منتج موجود
    public String giftProduct(int giverId, int receiverId, int productId, int merchantId) {
        User giver = userRepository.findById(giverId).orElse(null);
        User receiver = userRepository.findById(receiverId).orElse(null);

        if (giver == null || receiver == null) {
            return "User not found";
        }
        if (!giver.getRole().equalsIgnoreCase("Customer") || !receiver.getRole().equalsIgnoreCase("Customer")) {
            return "Only customers can gift products";
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return "Product not found";
        }

        MerchantStock merchantStock = merchantStockService.getMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            return "Merchant stock not found";
        }

        if (merchantStock.getStock() <= 0) {
            return "Product out of stock";
        }

        double price = giver.isPrime() ? product.getPrice() * 0.95 : product.getPrice();
        if (giver.getBalance() < price) {
            return "Insufficient balance";
        }

        merchantStock.setStock(merchantStock.getStock() - 1);
        merchantStockService.updateMerchantStock(merchantStock, merchantStock.getId());

        giver.setBalance(giver.getBalance() - price);
        userRepository.save(giver);

        GiftDetiles giftDetails = new GiftDetiles(giverId, productId, price, "Gifted to user with ID: " + receiverId);
        giftDetilesRepository.save(giftDetails);

        return "Product gifted successfully";
    }
}


