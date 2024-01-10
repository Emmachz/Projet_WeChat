package fr.pantheonsorbonne.ufr27.miage.exception;

public class UserNotExistingException extends Throwable {
    public UserNotExistingException(int idWC) {
        super("The following user does not exist in WeChat : " + idWC + ". The purchase can't we be paid ");
    }

}
