package fr.pantheonsorbonne.ufr27.miage.service;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.exception.EventNotFoundException;

public interface EventService {

    Booking book(Booking booking) throws EventNotFoundException;


}
