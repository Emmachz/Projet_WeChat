package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvent", nullable = false)
    private Integer idEvent;

    @Column(name = "category", nullable = false)
    private String category;
    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "hour", nullable = false)
    private String hour;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "level")
    private String level;
    @Column(name = "status")
    private String status;



    public Event(int id,String category, String region, String date, String hour, String description, String level, String  status) {
        this.idEvent=id;
        this.category = category;
        this.region = region;
        this.date = date;
        this.hour = hour;
        this.description = description;
        this.level=level;
        this.status=status;
    }

    public Event() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.hour = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.hour = status;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idevent) {
        this.idEvent = idevent;
    }

}