package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Alert;

public interface AlertService {

    void addAlert(Alert alert);
    String getRegionId(String regionName);

}
