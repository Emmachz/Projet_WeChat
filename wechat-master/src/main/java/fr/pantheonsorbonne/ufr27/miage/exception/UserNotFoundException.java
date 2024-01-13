package fr.pantheonsorbonne.ufr27.miage.exception;

public class UserNotFoundException extends Exception {

    public static class NoExistUserException extends Throwable {
        public NoExistUserException(String login) {
            super("This user : " + login + " is not exist in database !");
        }
    }
}