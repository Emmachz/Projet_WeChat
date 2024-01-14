package fr.pantheonsorbonne.ufr27.miage.exception;

public class UnsufficientWalletAmountToPay extends Throwable{

    public UnsufficientWalletAmountToPay() {
        super("User don't have enough money to pay with his wallet.");
    }
}
