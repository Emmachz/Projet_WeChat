package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.AlertDAO;
import fr.pantheonsorbonne.ufr27.miage.dao.AlertDAOImpl;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AlertServiceImpl implements AlertService {

    @PersistenceContext
    EntityManager em;

    @Inject
    AlertDAO alertDAO;

    @Override
    @Transactional
    public Alert alert(Alert alert) throws EventNotFoundException {
        try {


        } catch (NonUniqueResultException | NoResultException e) {
            throw new EventNotFoundException(alert.getId());
        }
        return alert;

    }

    public void addAlert(Alert alert){
        this.alertDAO.addAlert(alert);
    }

    public void checkRegion(Alert alert){
        this.alertDAO.checkRegion(alert);
    }

}
