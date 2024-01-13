package fr.pantheonsorbonne.ufr27.miage.dao;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;

public interface UserDAO {

    User findUser(Long userId) throws NoSuchUserException;

    User findUserByLogin(String userLogin) throws UserNotFoundException.NoExistUserException;

    void removeUser (Long userId);

    Giving upadateUserGiving (Giving give);
}
