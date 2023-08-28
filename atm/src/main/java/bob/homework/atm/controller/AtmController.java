package bob.homework.atm.controller;

/* TODO：
    ATM具有的功能：
    1. 用户认证 (银行卡id匹配密码) ✅
    2. 账户管理 （客户身份信息，持有银行卡信息）✅
    3. 交易处理 (存钱、取钱、查询余额) ✅
    4. 错误处理和异常 ✅
    5. 安全性和数据校验

*/

import bob.homework.atm.domain.Atm;
import bob.homework.atm.domain.BankCard;
import bob.homework.atm.domain.Consumer;
import bob.homework.atm.domain.Message;
import bob.homework.atm.mapper.AtmMapper;
import bob.homework.atm.mapper.BankCardMapper;
import bob.homework.atm.mapper.ConsumerMapper;
import bob.homework.atm.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

import com.alibaba.fastjson.JSONObject;

@Slf4j
@RestController // 是@Controller和@ResponseBody两者的结合 返回的是json格式的数据
public class AtmController {

    @Autowired
    ConsumerService consumerService;

    @Autowired
    private AtmMapper atmMapper;

    @Autowired
    private ConsumerMapper consumerMapper;

    @Autowired
    private BankCardMapper bankCardMapper;


    @GetMapping("/getAtm")
    public Atm getAtm() {

        try{
            Atm atm = new Atm();
            List<Map<String, Object>> atmList = atmMapper.getAllAtm();
            atm.setId(new Integer(atmList.get(0).get("id").toString()));

            boolean atmStatus = Boolean.parseBoolean(atmList.get(0).get("status").toString());

            if (atmStatus == true) {
                atm.setStatus(atmStatus);

                BankCard bankCard = new BankCard();
                atm.setBankCard(bankCard);

                Consumer consumer = new Consumer();

                // 获取已插入的银行卡信息
                Integer bankCardId = new Integer(atmList.get(0).get("bankcardId").toString());
                Map<String, Object> bankCardInfo = bankCardMapper.getBankCard(bankCardId);
                // 还原bankcard对象
                bankCard.setId(new Integer(bankCardInfo.get("id").toString()));
                bankCard.setPassword(bankCardInfo.get("password").toString());
                bankCard.setOwner(consumer);
                bankCard.setBalance(new BigDecimal(bankCardInfo.get("balance").toString()));

                // 获取银行卡的拥有者信息
                Integer consumerId = new Integer(bankCardInfo.get("owner").toString());

                Map<String, Object> consumerInfo = consumerMapper.getConsumer(consumerId);
                // 还原consumer对象
                consumer.setId(new Integer(consumerInfo.get("id").toString()));
                consumer.setName(consumerInfo.get("name").toString());
                consumer.setIdCard(consumerInfo.get("idCard").toString());
                consumer.setPhoneNumber(consumerInfo.get("phoneNumber").toString());

                // 还原consumer的cardList
                String cardListIdStr = consumerInfo.get("card_list").toString();
                List<String> cardListId = Arrays.asList(cardListIdStr.substring(1,cardListIdStr.length()-1).split(","));
                for (String cardIdStr: cardListId) {
                    // 获取每个银行卡信息并构建银行卡对象()
                    Integer cardId = Integer.parseInt(cardIdStr);
//                BankCard bankCardForConsumer = new BankCard();
//                Map<String, Object> cardInfo = bankCardMapper.getBankCard(cardId);
//                bankCardForConsumer.setId(new Integer(cardInfo.get("id").toString()));
//                bankCardForConsumer.setPassword(cardInfo.get("password").toString());
//                bankCardForConsumer.setOwner(consumer);
//                bankCardForConsumer.setBalance(new BigDecimal(cardInfo.get("balance").toString()));
                    // 将银行卡绑定到用户
                    consumer.addCard(cardId);
                }
            }
            return atm;
        }catch (Exception e) {
            throw e;
        }


    }

    @Transactional(rollbackFor=Exception.class)
    @PostMapping("/authorization/{id}/{password}")
    public boolean authorization(@PathVariable Integer id, @PathVariable String password) {
        try {
            // 获得ATM状态
            Atm atm = consumerService.getAtm();
            // 判断是否已经验证过银行卡账号密码
            if (atm.getStatus() == true) {
                log.info("已验证过银行卡账号密码。。。。");
                return false;
            }
            // 验证银行卡账号密码 查询银行卡列表
            // TODO: 静态银行卡账号密码表 -> 数据库 ✅
            Map<String, Object> bankCardInfo = bankCardMapper.getBankCard(id);
            if (bankCardInfo == null) {
                log.info("！！！！！！错误，没有该id的银行卡！！！！！");
                return false;
            }
            if (bankCardInfo.get("password").toString().equals(password)) {
                log.info("---------银行卡身份认证成功---------");
                atm.setStatus(true);
                // 构建当前插入银行卡对象
                BankCard bankCard = new BankCard();
                bankCard.setId(new Integer(bankCardInfo.get("id").toString()));
                bankCard.setPassword(bankCardInfo.get("password").toString());
                bankCard.setBalance(new BigDecimal(bankCardInfo.get("balance").toString()));
                // 构建银行卡拥有者信息
                Consumer consumer = new Consumer();
                Integer consumerId = new Integer(bankCardInfo.get("owner").toString());

                Map<String, Object> consumerInfo = consumerMapper.getConsumer(consumerId);
                // 还原consumer对象
                consumer.setId(new Integer(consumerInfo.get("id").toString()));
                consumer.setName(consumerInfo.get("name").toString());
                consumer.setIdCard(consumerInfo.get("idCard").toString());
                consumer.setPhoneNumber(consumerInfo.get("phoneNumber").toString());

                // 还原consumer的cardList
                String cardListIdStr = consumerInfo.get("card_list").toString();
                List<String> cardListId = Arrays.asList(cardListIdStr.substring(1,cardListIdStr.length()-1).split(","));
                for (String cardIdStr: cardListId) {
                    // 获取每个银行卡信息并构建银行卡对象()
                    Integer cardId = Integer.parseInt(cardIdStr);
//                BankCard bankCardForConsumer = new BankCard();
//                Map<String, Object> cardInfo = bankCardMapper.getBankCard(cardId);
//                bankCardForConsumer.setId(new Integer(cardInfo.get("id").toString()));
//                bankCardForConsumer.setPassword(cardInfo.get("password").toString());
//                bankCardForConsumer.setOwner(consumer);
//                bankCardForConsumer.setBalance(new BigDecimal(cardInfo.get("balance").toString()));
                    // 将银行卡绑定到用户
                    consumer.addCard(cardId);
                }
                // 银行卡绑定拥有者
                bankCard.setOwner(consumer);
                // atm机绑定银行卡
                atm.setBankCard(bankCard);

                // 更新数据库atm机状态
                atmMapper.updateAtm("true", bankCard.getId(), atm.getId());
                return true;
            }
            log.info("！！！！！！！！银行卡身份认证失败！！！！！！！！");
            return false;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e; // 继续抛出异常以通知上层调用者
        }

    }

    // 查询银行卡余额
    @GetMapping("/getBalance")
    public BigDecimal getBalance() {
        Atm atm = consumerService.getAtm();
        // 判断是否已经登陆
        if (atm.getStatus() == true) {
            log.info("已验证过银行卡账号密码。。。。");
            return atm.getBankCard().getBalance();
        }
        log.info("没插卡，不存在这种情况");
        return BigDecimal.valueOf(-1);
    }

    // 存钱/取钱/转账
    @Transactional(rollbackFor=Exception.class)
    @PostMapping("/operate")
    public boolean operateMoney(@RequestBody String jsonStr) {
        try {
            Message message = JSONObject.parseObject(jsonStr, Message.class);
            Atm atm = consumerService.getAtm();
            if (atm.getStatus() == false) {
                log.info("没插卡啊！");
                return false;
            }
            if (message.getBehavior().equals("saveMoney")) {
                bankCardMapper.saveMoney(message.getMoney(), atm.getBankCard().getId());
                // int i = 5 / 0;
                log.info("-----------存钱成功-------------");
                return true;
            } else if (message.getBehavior().equals("bringMoney")) {
                // 判断余额够不够
                BigDecimal balance = new BigDecimal(bankCardMapper.getBankCard(atm.getBankCard().getId()).get("balance").toString());
                if (balance.compareTo(message.getMoney()) < 0) {
                    log.info("钱不够，没法取");
                    return false;
                } else {
                    bankCardMapper.bringMoney(message.getMoney(), atm.getBankCard().getId());
                    log.info("该银行卡的余额为:" + bankCardMapper.getBankCard(atm.getBankCard().getId()).get("balance").toString());
                    return true;
                }
            } else if (message.getBehavior().equals("transferMoney")) {
                // 检测转账银行卡号是否存在
                if (!bankCardMapper.getAllId().contains(message.getBankCardId())) {
                    log.info("!!!!!!!!!!错误，银行卡号不存在!!!!!!!!!!");
                    return false;
                }

                BigDecimal balance = new BigDecimal(bankCardMapper.getBankCard(atm.getBankCard().getId()).get("balance").toString());

                if (balance.compareTo(message.getMoney()) < 0) {
                    log.info("钱不够，没法转账");
                    return false;
                } else {
                    bankCardMapper.bringMoney(message.getMoney(), atm.getBankCard().getId());
                    log.info("转出银行卡的余额为:" + bankCardMapper.getBankCard(atm.getBankCard().getId()).get("balance").toString());

                    bankCardMapper.saveMoney(message.getMoney(), message.getBankCardId());
                    log.info("转入银行卡的余额为:" + bankCardMapper.getBankCard(message.getBankCardId()).get("balance").toString());

                    return true;
                }
            } else {
                log.info("系统错误，没有该操作");
                return false;
            }
        } catch (Exception e) {
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e; // 继续抛出异常以通知上层调用者
        }

    }

    @GetMapping("/exitCard")
    @Transactional(rollbackFor=Exception.class)
    public boolean exitCard() {
        try {
            // 获得ATM状态
            Atm atm = consumerService.getAtm();
            if (atm.getStatus() == false) {
                log.info("！！！！！！没有已插入的银行卡，无法拔出！！！！！！！");
                return false;
            } else {
                atmMapper.updateAtm("false", null, atm.getId());
                log.info("----------成功推出银行卡----------");
                return true;
            }
        } catch (Exception e) {
            // 回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }
}
