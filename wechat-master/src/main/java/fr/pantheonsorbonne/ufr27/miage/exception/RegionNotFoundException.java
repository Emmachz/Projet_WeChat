package fr.pantheonsorbonne.ufr27.miage.exception;

public class RegionNotFoundException extends Throwable{

    public RegionNotFoundException(String region) {
        super("not enough quota for venue " + region);
    }
}
