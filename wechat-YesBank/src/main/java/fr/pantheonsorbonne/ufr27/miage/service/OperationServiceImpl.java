package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchAccountException;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientAmountInAccountException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OperationServiceImpl implements OperationService{
    @Inject
    BankDAO bankDAO;

    @Override
    @Transactional
    public TransfertArgent realizeOperation (TransfertArgent transfertArgent) throws NoSuchAccountException {
        UserLocal emetteur = transfertArgent.getEmetteur();
        UserLocal receveur = transfertArgent.getReceveur();
        double value = transfertArgent.getValue();
        if(!transfertArgent.getEmetteur().userNameBank().equals("YesBank") && !transfertArgent.getReceveur().userNameBank().equals("YesBank")){
            return new TransfertArgent(emetteur.userLogin(), receveur.userLogin(), transfertArgent.getValue());
        }else if (transfertArgent.getEmetteur().userNameBank().equals("YesBank") && transfertArgent.getReceveur().userNameBank().equals("YesBank")){
            if (bankDAO.checkSolde(emetteur.userNumeroBank(), value) && bankDAO.checkSolde(receveur.userNumeroBank(), value)){
                bankDAO.updateTwoComptes(transfertArgent);
                return transfertArgent;
            }
        } else if (transfertArgent.getEmetteur().userNameBank().equals("YesBank")) {
            if (bankDAO.checkSolde(emetteur.userNumeroBank(), value)){
                bankDAO.updateCompteCredit(transfertArgent);
                return transfertArgent;
            }
        }else if (transfertArgent.getReceveur().userNameBank().equals("YesBank")){
            if (bankDAO.checkSolde(receveur.userNumeroBank(), value)){
                bankDAO.updateCompteDebit(transfertArgent);
                return transfertArgent;
            }
        }
        return new TransfertArgent(emetteur.userLogin(), receveur.userLogin(), transfertArgent.getValue());

    }


    @Override
    @Transactional
    public void creditMoney(String bankNumber, double amount) throws NoSuchAccountException {
        bankDAO.addMoneyToAccount(bankNumber, amount);
    }

    @Override
    public void debitMoney(String bankNumber, double amount) throws UnsufficientAmountInAccountException, NoSuchAccountException {
        bankDAO.debitMoneyFromAccount(bankNumber, amount);
    }

    @Override
    @Transactional
    public Giving realizeOperationGiving(Giving give) throws NoSuchAccountException {
        if (bankDAO.checkSolde(give.getUsergive().userNumeroBank(), give.getQuantity())){
            bankDAO.updateGivingComptes(give);
            return give;
        }else{
            return new Giving(give.getDonationId(),give.getUsergive().userLogin(), give.getTypeGive(), give.getQuantity());
        }
    }


}
