package fr.pantheonsorbonne.ufr27.miage.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PurchaseDTO {
    String loginUser;

    String loginSeller;

    double amount;

    Long purchaseId;

    boolean isDebitOk;

    boolean isCreditOk;

    public PurchaseDTO()
    {

    }

    public PurchaseDTO(String loginUser,
                       String loginSeller,
                       double amount)
    {
        this.loginUser = loginUser;
        this.loginSeller = loginSeller;
        this.amount = amount;
        this.isDebitOk = false;
        this.isCreditOk = false;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getLoginSeller() {
        return loginSeller;
    }

    public void setLoginSeller(String loginSeller) {
        this.loginSeller = loginSeller;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public boolean isDebitOk() {
        return isDebitOk;
    }

    public void setDebitOk(boolean debitOk) {
        isDebitOk = debitOk;
    }

    public boolean isCreditOk() {
        return isCreditOk;
    }

    public void setCreditOk(boolean creditOk) {
        isCreditOk = creditOk;
    }
}
