package top.nextnet.service;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;

public interface UserGateway {
    void sendTransfertInfos(TransfertArgent transfertArgent);
}
