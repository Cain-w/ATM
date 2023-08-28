package bob.homework.atm.domain;

import java.math.BigDecimal;

public class BankCard {
    private Integer id;
    private String password;
    private Consumer owner;
    private BigDecimal balance;


    public BankCard() {
    }

    // 开通一张银行卡 必须得有密码和持有人
    public BankCard(String password, Consumer owner) {
        this.password = password;
        this.owner = owner;
    }

    public BankCard(Integer id, String password, Consumer owner, BigDecimal balance) {
        this.id = id;
        this.password = password;
        this.owner = owner;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Consumer getOwner() {
        return owner;
    }

    public void setOwner(Consumer owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
