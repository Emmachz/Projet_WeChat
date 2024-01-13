package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchComptException;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;

public interface OperationService {
    Giving realizeOperationGiving(Giving give) throws NoSuchComptException;
}
