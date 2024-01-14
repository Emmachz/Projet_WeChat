package fr.pantheonsorbonne.ufr27.miage.exception;

public class UserNotExistingException extends Throwable {
    public UserNotExistingException() {
        super("The following user does not exist in WeChat. The purchase can't we be paid ");
    }

}
