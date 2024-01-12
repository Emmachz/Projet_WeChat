package fr.pantheonsorbonne.ufr27.miage.exception;

public class QuantityGivenHigherThanDemandedException extends Throwable {
    public QuantityGivenHigherThanDemandedException(int quantityDemande) {
        super("You give much more than demanded : " + quantityDemande);
    }
}
