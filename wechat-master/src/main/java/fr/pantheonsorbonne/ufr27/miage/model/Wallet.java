package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "wallet_amount", nullable = false)
    private double walletAmount;

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }
    public Long getWalletId() {
        return walletId;
    }
    public void setUserId(User userId){
        this.userId = userId;
    }
    public User getUserId(){
        return userId;
    }
    public void setWalletAmount(double walletAmount){
        this.walletAmount = walletAmount;
    }
    public double getWalletAmount(){
        return walletAmount;
    }
}
