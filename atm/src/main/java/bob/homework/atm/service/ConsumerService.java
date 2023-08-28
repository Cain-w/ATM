package bob.homework.atm.service;

import bob.homework.atm.domain.Atm;
import bob.homework.atm.feign.ConsumerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConsumerService {
    @Autowired
    ConsumerFeignClient consumerFeignClient;

    public Atm getAtm() {
        return consumerFeignClient.getAtm();
    }


    public boolean authorization(String id, String password) {
        return consumerFeignClient.authorization(id, password);
    }

    public BigDecimal getBalance(String id) {
        return consumerFeignClient.getBalance(id);
    }

    public boolean operateMoney(String jsonStr) {
        return consumerFeignClient.operateMoney(jsonStr);
    }

    public boolean exitCard() {
        return consumerFeignClient.exitCard();
    }
}
