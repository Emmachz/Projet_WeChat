package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.BankDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OperationServiceImpl implements OperationService{

    @Inject
    BankDAO bankDAO;

    @Override
    @Transactional
    public Giving realizeOperationGiving(Giving give) throws NoSuchComptException {
        if (bankDAO.checkSolde(give.getUsergive().userNumeroBank(), give.getQuantity())){
            bankDAO.updateGivingComptes(give);
            return give;
        }else{
            return new Giving(give.getDonationId(),give.getUsergive().userLogin(), give.getTypeGive(), give.getQuantity());
        }
    }
}
