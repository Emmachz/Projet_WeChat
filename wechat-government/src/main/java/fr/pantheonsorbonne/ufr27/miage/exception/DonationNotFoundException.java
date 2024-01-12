package fr.pantheonsorbonne.ufr27.miage.exception;

public class DonationNotFoundException extends Throwable{

    public DonationNotFoundException(int Idevent) {
        super("not enough quota for donation " + Idevent);
    }

}