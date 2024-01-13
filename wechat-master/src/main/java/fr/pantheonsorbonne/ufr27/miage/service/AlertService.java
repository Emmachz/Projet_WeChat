package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;

public interface AlertService {

    Alert alert(Alert alert) throws EventNotFoundException;

    void addAlert(Alert alert);

}
