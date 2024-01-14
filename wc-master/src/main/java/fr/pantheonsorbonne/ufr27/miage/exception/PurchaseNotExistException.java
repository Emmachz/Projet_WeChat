package fr.pantheonsorbonne.ufr27.miage.exception;

public class PurchaseNotExistException extends Throwable{
    public PurchaseNotExistException() {
        super("This purchase doesn't exist");
    }
}
