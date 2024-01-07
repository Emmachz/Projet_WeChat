package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.model.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MessageDAOImpl implements MessageDAO {

    @Inject
    EntityManager em;

    @Transactional
    public void addMessage(Message message, int id){

    }

    @Transactional
    public void check(Message message){
        //int idMessage=message.getId();
        //String description=message.getDescription();
        //String region=message.getRegion();
        Message attachedMessage = em.merge(message);
        em.persist(attachedMessage);
    }
}
