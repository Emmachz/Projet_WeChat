package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Purchase;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TransferDAOImpl implements TransferDAO{

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Versement createTransfer(User sender, User receiver, double amount) {
        var transfer = new Versement(sender, receiver, amount);
        this.em.merge(transfer);
        this.em.flush();
        return transfer;
    }
}
