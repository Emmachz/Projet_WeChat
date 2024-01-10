package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface VersementService {

    Versement findTwoUsersVersement (TransfertArgent transfertArgent);

    TransfertArgent realizeVersementWallet (Versement versement);

    TransfertArgent sendInfosToBank(Versement versement);
}
