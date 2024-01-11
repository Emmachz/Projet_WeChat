package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VersementServiceImpl implements VersementService {

    @Inject
    UserDAO userDAO;

    @Override
    @Transactional
    public Versement findTwoUsersVersement (TransfertArgent transfertArgent){
        try{
            User emetteur = userDAO.findUserByLogin(transfertArgent.getLoginEmetteur());
            User receveur = userDAO.findUserByLogin(transfertArgent.getLoginReceveur());
            Versement versement = new Versement(emetteur, receveur, transfertArgent.getValue());
            return versement;
        } catch (UserNotFoundException.NoExistUserException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    @Transactional
    public TransfertArgent realizeVersementWallet (Versement versement){
        try{
            return userDAO.upadateUser(versement);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    @Transactional
    public TransfertArgent sendInfosToBank(Versement versement){
        UserLocal emetteur = new UserLocal(versement.getEmetteurId().getUserName(),versement.getEmetteurId().getUserLogin(), versement.getEmetteurId().getUserEmail(),versement.getEmetteurId().getUserNameBank(),versement.getEmetteurId().getUserNumeroBank());
        UserLocal receveur = new UserLocal(versement.getReceveurId().getUserName(),versement.getReceveurId().getUserLogin(), versement.getReceveurId().getUserEmail(),versement.getReceveurId().getUserNameBank(),versement.getReceveurId().getUserNumeroBank());
        return new TransfertArgent(emetteur, receveur, versement.getVersementAmount());
    }








}
