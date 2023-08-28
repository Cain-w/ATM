package bob.homework.atm.feign;

import bob.homework.atm.domain.Atm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@FeignClient(name = "atm")
public interface ConsumerFeignClient {
    @PostMapping("/authorization/{id}/{password}")
    public boolean authorization(@PathVariable("id") String id, @PathVariable("password") String password);

    @GetMapping("/getBalance")
    public BigDecimal getBalance(@PathVariable("id") String id);

    @GetMapping("/getAtm")
    public Atm getAtm();

    @PostMapping("/operate")
    public boolean operateMoney(@RequestBody String jsonStr);

    @GetMapping("/exitCard")
    public boolean exitCard();

}
