package fr.pantheonsorbonne.ufr27.miage.dao;


import fr.pantheonsorbonne.ufr27.miage.model.User;

public interface UserDAO {

    User findUser(Long userId) throws NoSuchUserException;

    void removeUser (Long userId);

    User createNewUser (String userName, String userEmail, String userRegion, String userPassword);
}