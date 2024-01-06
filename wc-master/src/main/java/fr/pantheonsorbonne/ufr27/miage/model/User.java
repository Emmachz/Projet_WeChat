package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
@Table(name = "User")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;
    @Column(name = "user_email", nullable = false, unique = true)
    private String userEmail;
    @Column(name = "user_region", length = 100)
    private String userRegion;
    @Column(name = "nameBank")
    private String nameBank;

    /*@OneToOne(optional = false)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bankId;*/

    public User(String userName, String userEmail, String userRegion){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRegion = userRegion;
    }

    public User() {

    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserName (String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }

    public void setUserRegion (String userRegion){
        this.userRegion = userRegion;
    }

    public String getUserRegion(){
        return userRegion;
    }




}
