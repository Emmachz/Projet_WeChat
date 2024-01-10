package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idES", nullable = false)
    private ExternalSeller idES;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idWC", nullable = false)
    private User idWC;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "isValidatedByUser", nullable = false)
    private boolean isValidatedByUser;

    public Purchase(ExternalSeller externalSeller,
                    User weChatUser,
                    int amount)
    {
        this.idES = externalSeller;
        this.idWC = weChatUser;
        this.amount = amount;
        this.createdAt = new Date();
        this.isValidatedByUser = false;
    }

    public Purchase()
    {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ExternalSeller getIdES() {
        return idES;
    }

    public void setIdES(ExternalSeller idES) {
        this.idES = idES;
    }

    public User getIdWC() {
        return idWC;
    }

    public void setIdWC(User idWC) {
        this.idWC = idWC;
    }

    public Integer getAmount() {
        return amount;
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
