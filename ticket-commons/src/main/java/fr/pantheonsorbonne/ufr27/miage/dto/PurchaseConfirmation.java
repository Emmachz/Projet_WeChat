package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseConfirmation {
    Long purchaseId;

    String userLogin;

    public PurchaseConfirmation(Long id, String login)
    {
        this.purchaseId = id;
        this.userLogin = login;
    }

    public PurchaseConfirmation(){}

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
