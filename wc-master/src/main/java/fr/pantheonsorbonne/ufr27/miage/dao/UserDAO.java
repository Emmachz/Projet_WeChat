package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsufficientWalletAmountToPay;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface UserDAO {

    User findUser(Long userId) throws NoSuchUserException;

    User findUserByLogin(String userLogin) throws UserNotFoundException.NoExistUserException;

    TransfertArgent upadateUser (Versement versement);

    void debitAmountToUser(String login, double amount) throws UnsufficientWalletAmountToPay;

    void creditAmountToUser(String login, double amount);

    Giving upadateUserGiving(Giving give);
}
