package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idES", nullable = false)
    private Integer idES;

    @Column(name = "idWC", nullable = false)
    private Integer idWC;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "isValidatedByUser", nullable = false)
    private boolean isValidatedByUser;

    public Purchase(int idES,
                    int idWC,
                    int amount)
    {
        this.idES = idES;
        this.idWC = idWC;
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

    public Integer getIdES() {
        return idES;
    }

    public void setIdES(Integer idES) {
        this.idES = idES;
    }

    public Integer getIdWC() {
        return idWC;
    }

    public void setIdWC(Integer idWC) {
        this.idWC = idWC;
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
