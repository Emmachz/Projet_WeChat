package fr.pantheonsorbonne.ufr27.miage.dto;

public class TransfertArgent {
    private String loginEmetteur;
    private String loginReceveur;
    private UserLocal emetteur;
    private UserLocal receveur;
    private double value;

    private boolean isDebitOk;

    private boolean isCreditOk;
    private Long transferId;

    public TransfertArgent(String loginEmetteur, String loginReceveur, double value){
        this.loginEmetteur = loginEmetteur;
        this.loginReceveur = loginReceveur;
        this.value = value;
        this.isCreditOk = false;
        this.isDebitOk = false;
    }

    public TransfertArgent(UserLocal emetteur, UserLocal receveur, double value){
        this.emetteur = emetteur;
        this.receveur = receveur;
        this.value = value;
    }

    public TransfertArgent(){}

    public void setLoginEmetteur(String loginEmetteur){
        this.loginEmetteur = loginEmetteur;
    }
    public String getLoginEmetteur(){
        return loginEmetteur;
    }
    public void setLoginReceveur(String loginReceveur){
        this.loginReceveur = loginReceveur;
    }
    public String getLoginReceveur(){
        return loginReceveur;
    }

    public void setEmetteur(UserLocal emetteur) {
        this.emetteur = emetteur;
    }
    public UserLocal getEmetteur(){
        return emetteur;
    }
    public void setReceveur(UserLocal receveur) {
        this.receveur = receveur;
    }
    public UserLocal getReceveur(){
        return receveur;
    }
    public void setValue(double value){
        this.value = value;
    }
    public double getValue(){
        return value;
    }

    public boolean isDebitOk() {
        return isDebitOk;
    }

    public void setDebitOk(boolean debitOk) {
        isDebitOk = debitOk;
    }

    public boolean isCreditOk() {
        return isCreditOk;
    }

    public void setCreditOk(boolean creditOk) {
        isCreditOk = creditOk;
    }

    public Long getTransferId() {
        return transferId;
    }

    public void setTransferId(Long transferId) {
        this.transferId = transferId;
    }
}
