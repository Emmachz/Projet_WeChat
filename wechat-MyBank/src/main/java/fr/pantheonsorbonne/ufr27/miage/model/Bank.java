package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;
@Table(name = "MyBank")
@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id", nullable = false)
    private Long bankId;

    @Column(name = "bank_number", nullable = false, unique = true)
    private String bankNumber;

    @Column(name = "bank_amount")
    private double bankAmonut;

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
    public Long getBankId() {
        return bankId;
    }
    public void setBankNumber(String bankNumber){
        this.bankNumber = bankNumber;
    }
    public String getBankNumber(){
        return bankNumber;
    }
    public void setBankAmount(double bankAmonut){
        this.bankAmonut = bankAmonut;
    }
    public double getBankAmonut(){
        return bankAmonut;
    }

}
