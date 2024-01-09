package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.Collection;
@ApplicationScoped
public class AlDAOImpl implements ALDAO {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void check(Alert alert){

        //Alert newAlert= new Alert(4,"addad","zddff");

        //em.merge(newAlert);


        //return em.createQuery("SELECT Alert FROM Alert alert", Alert.class).getResultList();

        //em.persist(attachedEvent);
        //Alert attachedAlert = em.merge(alert);

        em.merge(alert);
        //System.out.println("rentréeeeeeeee");
        //em.persist(alert);

        //Alert attachedAlert = em.merge(alert);
        //em.persist(attachedAlert);
    }
}
