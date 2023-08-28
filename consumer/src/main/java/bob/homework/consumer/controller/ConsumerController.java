package bob.homework.consumer.controller;

import bob.homework.consumer.domain.Message;
import bob.homework.consumer.service.AtmService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
public class ConsumerController {

    @Autowired
    AtmService atmService;

    @PostMapping("/authorization/{id}/{password}")
    public boolean authorization(@PathVariable Integer id, @PathVariable String password) {
        boolean ReqStatus =  atmService.authorization(id, password);
        if (ReqStatus == true) {
            log.info("-----------银行卡认证成功------------");
        } else {
            log.info("!!!!!!!!!!银行卡认证失败!!!!!!!!!!！！");
        }
        return ReqStatus;
    }

    @GetMapping("/getBalance")
    public BigDecimal getBalance() {
        BigDecimal balance = atmService.getBalance();
        log.info("该银行卡的余额为："+balance);
        return balance;
    }

    @PostMapping("/operate")
    public boolean operateMoney(@RequestBody String jsonStr) {
        Message message = JSONObject.parseObject(jsonStr, Message.class);
        boolean ReqStatus =  atmService.operateMoney(jsonStr);
        if (ReqStatus == false) {
            log.info("!!!!!!!!!!!!!操作失败!!!!!!!!!!!!!");
        } else {
            if (message.getBehavior().equals("saveMoney")) {
                log.info("存款成功, 剩余金额为："+ atmService.getBalance());
            } else if (message.getBehavior().equals("bringMoney")) {
                log.info("取款成功, 剩余金额为："+ atmService.getBalance());
            } else {
                log.info("转账成功, 剩余金额为："+ atmService.getBalance());
            }
        }
        return ReqStatus;
    }

    @GetMapping("/exitCard")
    public boolean exitCard() {
        boolean ReqStatus =  atmService.exitCard();
        if (ReqStatus == false) {
            log.info("！！！！！！推出银行卡失败！！！！！！！");
            return false;
        } else {
            log.info("----------成功推出银行卡----------");
            return true;
        }
    }
}
