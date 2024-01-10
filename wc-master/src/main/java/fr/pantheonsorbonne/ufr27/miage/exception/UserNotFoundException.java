package fr.pantheonsorbonne.ufr27.miage.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(int idWC) {
        super("The following user does not exist in WeChat : " + idWC + ". The purchase can't we be paid ");
    }

}
