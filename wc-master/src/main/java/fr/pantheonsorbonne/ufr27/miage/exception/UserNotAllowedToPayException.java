package fr.pantheonsorbonne.ufr27.miage.exception;

public class UserNotAllowedToPayException extends Throwable {
    public UserNotAllowedToPayException() {
        super("You are not allowed to pay this purchase.");
    }
}
