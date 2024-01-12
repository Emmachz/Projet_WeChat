package top.nextnet.camel.gateways;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;

public interface GivingGateway {
    void sendGivingOrder(Giving giving);
}
