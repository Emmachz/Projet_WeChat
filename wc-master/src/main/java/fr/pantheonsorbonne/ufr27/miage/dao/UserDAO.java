package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;

public interface UserDAO {

    User findUser(Long userId) throws NoSuchUserException;

    User findUserByLogin(String userLogin) throws UserNotFoundException.NoExistUserException;

    TransfertArgent upadateUser (Versement versement);

    void removeUser (Long userId);

}
