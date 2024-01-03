package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

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
    @Column(name = "user_password", nullable = false, length = 8)
    private String userPassword;

    @OneToOne(optional = false)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bankId;

    public User(String userName, String userEmail, String userRegion, String userPassword){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRegion = userRegion;
        this.userPassword = userPassword;
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

    public void setUserEmail (String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserRegion (String userRegion){
        this.userRegion = userRegion;
    }

    public String getUserRegion(){
        return userRegion;
    }
    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }

    public String getUserPassword(){
        return userPassword;
    }
    public void setBankIdId(Bank bankId){
        this.bankId = bankId;
    }
    public Bank getBankId(){
        return bankId;
    }



}
