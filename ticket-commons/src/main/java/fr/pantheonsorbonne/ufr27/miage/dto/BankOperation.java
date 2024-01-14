package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class BankOperation implements Serializable {

    Long weChatTransferId;

    String userNumeroBank;

    String bankName;

    double amount;

    boolean isComplete;

    public BankOperation(){}

    public BankOperation(String userNumeroBank,
                         String bankName,
                         double amount)
    {
        this.userNumeroBank = userNumeroBank;
        this.bankName = bankName;
        this.amount = amount;
        this.isComplete = false;
    }

    public String getUserNumeroBank() {
        return userNumeroBank;
    }

    public void setUserNumeroBank(String userNumeroBank) {
        this.userNumeroBank = userNumeroBank;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Long getWeChatTransferId() {
        return weChatTransferId;
    }

    public void setWeChatTransferId(Long weChatTransferId) {
        this.weChatTransferId = weChatTransferId;
    }
}
