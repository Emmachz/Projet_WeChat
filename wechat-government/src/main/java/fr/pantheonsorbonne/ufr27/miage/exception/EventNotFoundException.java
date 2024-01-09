package fr.pantheonsorbonne.ufr27.miage.exception;

public class EventNotFoundException extends Throwable{

    public EventNotFoundException(int Idevent) {
        super("not enough quota for venue " + Idevent);
    }

}
