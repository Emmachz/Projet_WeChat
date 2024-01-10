package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class ExternalSeller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ES", nullable = false)
    private Long esId;

    @Column(name = "email_ES", nullable = false, unique = true)
    private String emailES;

    public Long getEsId() {
        return esId;
    }

    public void setEsId(Long esId) {
        this.esId = esId;
    }

    public String getEmailES() {
        return emailES;
    }

    public void setEmailES(String emailES) {
        this.emailES = emailES;
    }
}
