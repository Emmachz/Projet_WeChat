package fr.pantheonsorbonne.ufr27.miage.dto;

public class UserLocal {
    private String userName;
    private String userLogin;
    private String userEmail;
    private String userNameBank;
    private String userNumeroBank;

    public UserLocal(String userName, String userLogin, String userEmail, String userNameBank, String userNumeroBank){
        this.userName = userName;
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userNameBank = userNameBank;
        this.userNumeroBank = userNumeroBank;
    }

    public UserLocal(){}

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

    public void setUserEmail (String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserEmail(){
        return userEmail;
    }


    public void setUserNameBank (String bankName){
        this.userNameBank = bankName;
    }

    public String getUserNameBank(){
        return userNameBank;
    }

    public void setUserNumeroBank(String numeroBank){
        this.userNumeroBank = numeroBank;
    }
    public String getUserNumeroBank(){
        return userNumeroBank;
    }

}