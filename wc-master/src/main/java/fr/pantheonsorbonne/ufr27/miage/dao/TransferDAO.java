package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface TransferDAO {

    public Versement createTransfer(User sender, User receiver, double amount);
}
