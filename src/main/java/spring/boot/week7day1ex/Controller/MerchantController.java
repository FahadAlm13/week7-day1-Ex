package spring.boot.week7day1ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day1ex.Model.Merchant;
import spring.boot.week7day1ex.Service.MerchantService;


@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchants() {
        return ResponseEntity.status(200).body(merchantService.getMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("Merchant added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@Valid @RequestBody Merchant merchant, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body((message));
        }
        if (merchantService.updateMerchant(merchant, id)) {
            return ResponseEntity.status(200).body("Merchant updated successfully");
        } else return ResponseEntity.status(404).body("Merchant not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        if (merchantService.deleteMerchant(id)) {
            return ResponseEntity.status(200).body("Merchant deleted successfully");
        } else return ResponseEntity.status(404).body("Merchant not found");
    }
}

