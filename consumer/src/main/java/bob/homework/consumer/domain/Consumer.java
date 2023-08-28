package bob.homework.consumer.domain;

import java.util.ArrayList;

public class Consumer {
    private Integer id;
    private String name;
    private String idCard;
    private String phoneNumber;
    private ArrayList<BankCard> cardList = new ArrayList<BankCard>();
}
