package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;

@ApplicationScoped
public class AlertServiceImpl implements AlertService {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Alert alert(Alert alert) throws EventNotFoundException {
        try {


        } catch (NonUniqueResultException | NoResultException e) {
            throw new EventNotFoundException(alert.getAlertId());
        }
        return alert;

    }
    @Transactional
    public void testService(Alert alert){
        System.out.println(alert+"test du service");
    }

}
