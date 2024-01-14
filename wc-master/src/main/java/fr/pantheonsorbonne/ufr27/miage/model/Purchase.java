package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idES", nullable = false)
    private ExternalSeller externalSeller;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idWC", nullable = false)
    private User user;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "isValidatedByUser", nullable = false)
    private boolean isValidatedByUser;

    public Purchase(ExternalSeller externalSeller,
                    User weChatUser,
                    double amount)
    {
        this.externalSeller = externalSeller;
        this.user = weChatUser;
        this.amount = amount;
        this.createdAt = new Date();
        this.isValidatedByUser = false;
    }

    public Purchase()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExternalSeller getExternalSeller() {
        return externalSeller;
    }

    public void setExternalSeller(ExternalSeller externalSeller) {
        this.externalSeller = externalSeller;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isValidatedByUser() {
        return isValidatedByUser;
    }

    public void setValidatedByUser(boolean validatedByUser) {
        isValidatedByUser = validatedByUser;
    }
}
