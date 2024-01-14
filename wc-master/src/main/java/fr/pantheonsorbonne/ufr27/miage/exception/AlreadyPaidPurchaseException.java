package fr.pantheonsorbonne.ufr27.miage.exception;

public class AlreadyPaidPurchaseException extends Throwable{
    public AlreadyPaidPurchaseException() {
        super("This purchase has already been confirmed.");
    }
}
