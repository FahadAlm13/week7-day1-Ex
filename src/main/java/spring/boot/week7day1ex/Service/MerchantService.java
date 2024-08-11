package spring.boot.week7day1ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day1ex.Model.Merchant;
import spring.boot.week7day1ex.Repository.MerchantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getMerchant() {
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }

    public boolean updateMerchant(Merchant merchant, Integer id) {
        Merchant c = merchantRepository.getById(id);
        if (c == null) {
            return false;
        }
        c.setName(merchant.getName());
        merchantRepository.save(c);
        return true;
    }

    public boolean deleteMerchant(Integer id) {
        Merchant merchant = merchantRepository.getById(id);
        if (merchant == null) {
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }
}
