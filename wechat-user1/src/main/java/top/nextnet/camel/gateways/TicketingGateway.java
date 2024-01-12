package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.ETicket;

public interface TicketingGateway {

    void submitETicket(ETicket ticker);
}
