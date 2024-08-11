package spring.boot.week7day1ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day1ex.Model.GiftDetiles;
import spring.boot.week7day1ex.Model.User;
import spring.boot.week7day1ex.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@Valid @RequestBody User user, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("User added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (userService.updateUser(user, id)) {
            return ResponseEntity.status(200).body("User updated successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.status(200).body("User deleted successfully");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyProduct(@RequestParam Integer userId, @RequestParam int productId, @RequestParam int merchantId) {
        String result = userService.buyProduct(userId, productId, merchantId);
        if (result.contains("successfully")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }

    // Subscribe to Prime
    @PostMapping("/subscribePrime/{userId}")
    public ResponseEntity subscribePrime(@PathVariable Integer userId) {
        boolean result = userService.subscribePrime(userId);
        if (result) {
            return ResponseEntity.status(200).body("User subscribed to Prime successfully");
        } else {
            return ResponseEntity.status(400).body("Subscription to Prime failed");
        }
    }

    @GetMapping("/giftDetails/{userId}")
    public ResponseEntity getGiftDetails(@PathVariable Integer userId) {
        List<GiftDetiles> giftDetails = userService.getGiftByUserDetails(userId);
        if (giftDetails.isEmpty()) {
            return ResponseEntity.status(404).body("No gift details found for this user");
        }
        return ResponseEntity.status(200).body(giftDetails);
    }

    @PostMapping("/gift")
    public ResponseEntity<String> giftProduct(
            @RequestParam int giverId,
            @RequestParam int receiverId,
            @RequestParam int productId,
            @RequestParam int merchantId) {

        String result = userService.giftProduct(giverId, receiverId, productId, merchantId);
        if (result.contains("successfully")) {
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(400).body(result);
        }
    }
}
