package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseConfirmation {
    int purchaseId;

    String userMail;

    public PurchaseConfirmation(int id, String mail)
    {
        this.purchaseId = id;
        this.userMail = mail;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
