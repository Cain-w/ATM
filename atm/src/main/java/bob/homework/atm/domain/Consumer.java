package bob.homework.atm.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Consumer {
    private Integer id;
    private String name;
    private String idCard;
    private String phoneNumber;
    private ArrayList<Integer> cardIdList = new ArrayList<Integer>();

    // 无参构造
    public Consumer() {
    }

    // 初始化一个顾客时至少提供姓名、身份证和电话号码
    public Consumer(String name, String idCard, String phoneNumber) {
        this.name = name;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
    }

    public Consumer(Integer id, String name, String idCard, String phoneNumber, ArrayList<Integer> cardIdList) {
        this.id = id;
        this.name = name;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.cardIdList = cardIdList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Integer> getCardIdList() {
        return cardIdList;
    }

    public void setCardIdList(ArrayList<Integer> cardIdList) {
        this.cardIdList = cardIdList;
    }

    public void addCard(Integer bankCardId) {
//        if (this.list_card.equals(null)) {
//            this.list_card = new ArrayList<BankCard>();
//        }
        this.cardIdList.add(bankCardId);
    }
}
