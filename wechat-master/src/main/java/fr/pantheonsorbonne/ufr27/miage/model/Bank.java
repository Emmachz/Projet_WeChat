package fr.pantheonsorbonne.ufr27.miage.model;

import jakarta.persistence.*;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id", nullable = false)
    private Long bankId;

    @Column(name = "bank_number", nullable = false, length = 11, unique = true)
    private int bankNumber;

    @Column(name = "bank_amount")
    private double bankAmonut;

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
    public Long getBankId() {
        return bankId;
    }
    public void setBankNumber(int bankNumber){
        this.bankNumber = bankNumber;
    }
    public int getBankNumber(){
        return bankNumber;
    }
    public void setBankAmonut(double bankAmonut){
        this.bankAmonut = bankAmonut;
    }
    public double getBankAmonut(){
        return bankAmonut;
    }

}
