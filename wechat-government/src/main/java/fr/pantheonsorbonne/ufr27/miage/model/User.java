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
    @Column(name = "user_login", nullable = false, unique = true)
    private String userLogin;
    @Column(name = "user_region", length = 100)
    private String userRegion;
    @Column(name = "user_NameBank")
    private String userNameBank;

    public User(String userName, String userLogin, String userRegion, String userPassword){
        this.userName = userName;
        this.userLogin = userLogin;
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

    public void setUserLogin (String userLogin){
        this.userLogin = userLogin;
    }

    public String getUserLogin(){
        return userLogin;
    }

    public void setUserRegion (String userRegion){
        this.userRegion = userRegion;
    }

    public String getUserRegion(){
        return userRegion;
    }

    public void setUserNameBank (String bankName){
        this.userNameBank = bankName;
    }

    public String getUserNameBank(){
        return userNameBank;
    }



}