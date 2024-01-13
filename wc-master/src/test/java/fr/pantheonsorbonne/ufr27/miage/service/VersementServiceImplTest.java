package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dao.UserDAO;
import fr.pantheonsorbonne.ufr27.miage.dto.TransfertArgent;
import fr.pantheonsorbonne.ufr27.miage.dto.UserLocal;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.User;
import fr.pantheonsorbonne.ufr27.miage.model.Versement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class VersementServiceImplTest {
    @InjectMocks
    VersementServiceImpl versementService;
    @Mock
    UserDAO userDAO;

    @BeforeEach
    public void setup() throws UserNotFoundException.NoExistUserException {
        User emetteur = new User("liu", "haliu", "mail@gmail.com", "paris", 20, "MyBank", "1234567890");
        User receveur = new User("ounissi", "onimyriam", "mail1@gmail.com", "paris", 50, "MyBank", "0234567890");

        UserLocal emetteurLocal = new UserLocal(emetteur.getUserName(), emetteur.getUserLogin(), emetteur.getUserEmail(), emetteur.getUserNameBank(), emetteur.getUserNumeroBank());
        UserLocal receveurLocal = new UserLocal(receveur.getUserName(), receveur.getUserLogin(), receveur.getUserEmail(), receveur.getUserNameBank(), receveur.getUserNumeroBank());
        lenient().when(userDAO.findUserByLogin("haliu")).thenReturn(emetteur);
        lenient().when(userDAO.findUserByLogin("onimyriam")).thenReturn(receveur);
        lenient().when(userDAO.upadateUser(any())).thenAnswer((Answer<TransfertArgent>) invocationOnMock -> new TransfertArgent(emetteurLocal, receveurLocal, 5));
    }

    @Test
    public void findTwoUserTransfert () throws UserNotFoundException.NoExistUserException {
        Versement test = versementService.findTwoUsersVersement(new TransfertArgent("haliu", "onimyriam", 5));
        assertEquals(5, test.getVersementAmount());
    }

    @Test
    public void realizeVersementWallet(){
        TransfertArgent test = versementService.realizeVersementWallet(any());
        assertEquals(5, test.getValue());
    }

    @Test
    public void sendInfosToBank(){
        User emetteur = new User("liu", "haliu", "mail@gmail.com", "paris", 20, "MyBank", "1234567890");
        User receveur = new User("ounissi", "onimyriam", "mail1@gmail.com", "paris", 50, "MyBank", "0234567890");
        TransfertArgent test = versementService.sendInfosToBank(new Versement(emetteur, receveur, 5));
        assertEquals(5, test.getValue());
    }



}
