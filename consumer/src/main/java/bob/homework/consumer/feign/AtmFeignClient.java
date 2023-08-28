package bob.homework.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(name = "atm")
public interface AtmFeignClient {

    @PostMapping("/authorization/{id}/{password}")
    public boolean authorization(@PathVariable("id") Integer id, @PathVariable("password") String password);

    @GetMapping("/getBalance")
    public BigDecimal getBalance();

    @PostMapping("/operate")
    public boolean operateMoney(@RequestBody String jsonStr);

    @GetMapping("/exitCard")
    public boolean exitCard();

}
