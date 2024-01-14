package fr.pantheonsorbonne.ufr27.miage.dao;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AlertDAOImpl implements AlertDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void addAlert(Alert alert) {
        em.merge(alert);
    }

    @Override
    @Transactional
    public void addAlertAllRegion(Alert alert){
        em.merge((alert));
    }

}
