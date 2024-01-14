package fr.pantheonsorbonne.ufr27.miage.exception;

public class SellerNotRegisteredException extends Throwable {
    public SellerNotRegisteredException() {
        super("You have not been registered in WeChat. Create an account to sold by WeChat.");
    }
}
