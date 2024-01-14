package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import fr.pantheonsorbonne.ufr27.miage.service.VersementService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class VersementGateway {

    @Inject
    VersementService versementService;

    public TransfertArgent realizeVersementWallet (Versement versement){
        return versementService.realizeVersementWallet(versement);
    }
    public TransfertArgent sendInfosToBank(Versement versement){
        return versementService.sendInfosToBank(versement);
    }

    public Versement findTwoUsersVersement (TransfertArgent transfertArgent) throws UserNotFoundException.NoExistUserException { return versementService.findTwoUsersVersement(transfertArgent);}


}
