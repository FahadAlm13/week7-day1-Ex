package spring.boot.week7day1ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day1ex.Model.MerchantStock;
import spring.boot.week7day1ex.Service.MerchantStockService;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStocks() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);

        return ResponseEntity.status(200).body("MerchantStock added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@Valid @RequestBody MerchantStock merchantStock, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (merchantStockService.updateMerchantStock(merchantStock, id)) {
            return ResponseEntity.status(200).body("MerchantStock updated successfully");
        }
        return ResponseEntity.status(404).body("MerchantStock not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        if (merchantStockService.deleteMerchantStock(id)) {
            return ResponseEntity.status(200).body("MerchantStock deleted successfully");
        }
        return ResponseEntity.status(404).body("MerchantStock not found");
    }

    @PutMapping("/addStock")
    public ResponseEntity addStock(@RequestParam int productId, @RequestParam int merchantId, @RequestParam int amount) {
        if (merchantStockService.addMoreStock(productId, merchantId, amount)) {
            return ResponseEntity.status(200).body("product buy successfully");
        }
        return ResponseEntity.status(404).body("product you want to buy not available");
    }
}
