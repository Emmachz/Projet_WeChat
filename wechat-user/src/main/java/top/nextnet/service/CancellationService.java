package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.CancelationNotice;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import top.nextnet.cli.UserInterface;



@ApplicationScoped
public class CancellationService {

    @Inject
    UserInterface commerce;

    public void handleCancel(CancelationNotice notice){
        commerce.showErrorMessage("Ticket " + notice.getTicketId()+" is cancel");

    }
}
