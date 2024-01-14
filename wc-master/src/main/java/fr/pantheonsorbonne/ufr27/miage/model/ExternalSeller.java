package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExternalSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ES", nullable = false)
    private Long esId;

    @Column(name = "login_seller", nullable = false, unique = true)
    private String loginSeller;

    @Column(name = "seller_nameBank")
    private String sellerNameBank;
    @Column(name = "seller_numeroBank")
    private String sellerNumeroBank;

    public Long getEsId() {
        return esId;
    }

    public void setEsId(Long esId) {
        this.esId = esId;
    }

    public String getLoginSeller() {
        return loginSeller;
    }

    public void setLoginSeller(String loginSeller) {
        this.loginSeller = loginSeller;
    }

    public String getSellerNameBank() {
        return sellerNameBank;
    }

    public void setSellerNameBank(String sellerNameBank) {
        this.sellerNameBank = sellerNameBank;
    }

    public String getSellerNumeroBank() {
        return sellerNumeroBank;
    }

    public void setSellerNumeroBank(String sellerNumeroBank) {
        this.sellerNumeroBank = sellerNumeroBank;
    }
}
