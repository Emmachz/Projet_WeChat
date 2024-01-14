package fr.pantheonsorbonne.ufr27.miage.exception;

public class UnsufficientAmountInAccountException extends Throwable{
    public UnsufficientAmountInAccountException() {
        super("User don't have enough money to pay with his account.");
    }
}
