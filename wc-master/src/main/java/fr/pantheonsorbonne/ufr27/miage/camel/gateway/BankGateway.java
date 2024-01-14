package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;

public interface BankGateway {
    void sendDebitToBank(TransfertArgent transfer);
}
