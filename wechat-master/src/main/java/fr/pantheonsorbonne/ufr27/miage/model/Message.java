package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

//@Table(name = "Message", indexes = {
 //       @Index(name = "fk_Ticket_2_idx", columnList = "idUser"),

//})
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMessage", nullable = false)
    private Integer idMessage;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idUser2", nullable = true)
    private User idUser2;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "region", nullable = false)
    private String region;

    public Message (int id, String description, String region){
        this.idMessage=id;
        this.description=description;
        this.region=region;
    }

    public Message() {
    }

    public Integer getId() {
        return idMessage;
    }

    public void setId(Integer id) {
        this.idMessage = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }





}
