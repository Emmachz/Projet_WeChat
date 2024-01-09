package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Event;
import fr.pantheonsorbonne.ufr27.miage.model.Message;

import java.util.Collection;

public interface AlertService {

    Alert alert(Alert alert) throws EventNotFoundException;

    void addAlert(Alert alert);

    void checkRegion(Alert alert);

}
