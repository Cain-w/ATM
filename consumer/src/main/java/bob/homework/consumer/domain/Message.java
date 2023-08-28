package bob.homework.consumer.domain;

import java.math.BigDecimal;

public class Message {
    private String behavior;
    private BigDecimal money;

    private Integer bankCardId;

    public Integer getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Integer bankCardId) {
        this.bankCardId = bankCardId;
    }

    public Message(String behavior, BigDecimal money, Integer bankCardId) {
        this.behavior = behavior;
        this.money = money;
        this.bankCardId = bankCardId;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
