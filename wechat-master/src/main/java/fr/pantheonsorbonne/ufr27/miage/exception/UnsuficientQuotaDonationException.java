package fr.pantheonsorbonne.ufr27.miage.exception;

public class UnsuficientQuotaDonationException extends Throwable {
    public UnsuficientQuotaDonationException(double quantity, String typeGive) {
        super("too much giving ! You give " + quantity + " for "+ typeGive +" give");
    }
}