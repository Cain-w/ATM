package bob.homework.consumer.service;

import bob.homework.consumer.feign.AtmFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AtmService {
    @Autowired
    AtmFeignClient atmFeignClient;

    public boolean authorization(Integer id, String password) {
        return atmFeignClient.authorization(id, password);
    }

    public BigDecimal getBalance() {
        return atmFeignClient.getBalance();
    }

    public boolean operateMoney(String jsonStr) {
        return atmFeignClient.operateMoney(jsonStr);
    }

    public boolean exitCard() {
        return atmFeignClient.exitCard();
    }
}
