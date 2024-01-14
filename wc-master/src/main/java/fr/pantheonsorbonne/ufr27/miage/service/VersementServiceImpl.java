package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.ExternalSellerDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.TransferDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.BankOperation;
import fr.pantheonsorbonne.ufr27.miage.dto.PurchaseDTO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.SellerNotRegisteredException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.ExternalSeller;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VersementServiceImpl implements VersementService {

    @Inject
    UserDAO userDAO;

    @Inject
    ExternalSellerDAO esDAO;

    @Inject
    TransferDAO transferDAO;

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
    public TransfertArgent initTransfer(TransfertArgent transfertArgent) throws UserNotFoundException.NoExistUserException {
        User sender = userDAO.findUserByLogin(transfertArgent.getLoginEmetteur());
        User receiver = userDAO.findUserByLogin(transfertArgent.getLoginReceveur());
        var entityTransfer = this.transferDAO.createTransfer(sender, receiver, transfertArgent.getValue());
        transfertArgent.setTransferId(entityTransfer.getVersementId());
        return transfertArgent;
    }

    @Override
    @Transactional
    public BankOperation findBankDebitInfosFromTransfer(TransfertArgent transfer) {
        try
        {
            User sender = this.userDAO.findUserByLogin(transfer.getLoginEmetteur());
            return new BankOperation(sender.getUserNumeroBank(), sender.getUserNameBank(), transfer.getValue());
        }catch (UserNotFoundException.NoExistUserException e)
        {
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional
    public BankOperation findBankDebitInfosFromPurchase(PurchaseDTO purchase) {
        try
        {
            User sender = this.userDAO.findUserByLogin(purchase.getLoginUser());
            return new BankOperation(sender.getUserNumeroBank(), sender.getUserNameBank(), purchase.getAmount());
        } catch (UserNotFoundException.NoExistUserException e)
        {
            throw new RuntimeException();
        }
    }

    @Override
    public BankOperation findBankCreditInfosFromPurchase(PurchaseDTO purchase) {
        try
        {
            ExternalSeller receiver = this.esDAO.findSellerByLogin(purchase.getLoginSeller());
            return new BankOperation(receiver.getSellerNumeroBank(), receiver.getSellerNameBank(), purchase.getAmount());
        } catch (SellerNotRegisteredException e)
        {
            throw new RuntimeException();
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
