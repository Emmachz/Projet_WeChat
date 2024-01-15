package fr.pantheonsorbonne.ufr27.miage.exception;

public class DonationNotFoundException extends Throwable{

    public DonationNotFoundException(int donationId) {
        super("not enough quota for venue " + donationId);
    }

}
