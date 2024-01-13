package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class MessageServiceImpl implements MessageService{
    @PersistenceContext
    EntityManager em;

    public void checkMessage(Message message){
        System.out.println(message);
        //System.out.println(id);
        System.out.println("message bien recu");
        //this.messageDAO.check(message);
    }

    public void messageService(Message message){
        //this.messageDAO.check(message);
    }




}
