package spring.boot.week7day1ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day1ex.Model.MerchantStock;
import spring.boot.week7day1ex.Repository.MerchantStockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStockRepository;

    public List<MerchantStock> getMerchantStocks() {
        return merchantStockRepository.findAll();
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStockRepository.save(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStock merchantStock, Integer id) {
       MerchantStock m = merchantStockRepository.getById(id);
       if(m == null) {
           return false;
       }
       m.setId(merchantStock.getId());
       m.setProductId(m.getProductId());
       m.setStock(m.getStock());
       merchantStockRepository.save(m);
       return true;
    }

    public boolean deleteMerchantStock(Integer id) {
      MerchantStock m = merchantStockRepository.getById(id);
      if(m == null) {
          return false;
      }
      merchantStockRepository.deleteById(id);
      return true;
    }

    //Create endpoint where user can add more stocks of product to a merchant Stock
    public boolean addMoreStock(int productId, int merchantId, int amount) {
        for(MerchantStock merchantStock : merchantStockRepository.findAll()) {
            if (merchantStock.getProductId() == productId && merchantStock.getMerchantId() == merchantId) {
                merchantStock.setStock(merchantStock.getStock() + amount);
                return true;
            }
        }
        return false;
    }

    public MerchantStock getMerchantStock(int productId, int merchantId) {
        for (MerchantStock merchantStock : merchantStockRepository.findAll()) {
            if (merchantStock.getProductId() == productId && merchantStock.getMerchantId() == merchantId) {
                return merchantStock;
            }
        }
        return null;
    }

}
