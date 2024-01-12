package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseDTO {
    int weChatUserId;

    int externalSellerId;

    int amount;

    public PurchaseDTO()
    {

    }

    public PurchaseDTO(int idWC,
                       int idES,
                       int amount)
    {
        this.weChatUserId = idWC;
        this.externalSellerId = idES;
        this.amount = amount;
    }

    public int getWeChatUserId() {
        return weChatUserId;
    }

    public void setWeChatUserId(int weChatUserId) {
        this.weChatUserId = weChatUserId;
    }

    public int getExternalSellerId() {
        return externalSellerId;
    }

    public void setExternalSellerId(int idES) {
        this.externalSellerId = idES;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}