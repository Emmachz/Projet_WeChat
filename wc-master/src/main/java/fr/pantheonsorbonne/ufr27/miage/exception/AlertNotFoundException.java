package fr.pantheonsorbonne.ufr27.miage.exception;

public class AlertNotFoundException extends Throwable{

    public AlertNotFoundException(int IdAvent) {
        super("not enough quota for venue " + IdAvent);
    }

}
