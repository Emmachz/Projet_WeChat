package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dao.NoSuchTicketException;
import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.CustomerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.ExpiredTransitionalTicketException;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.service.GivingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GivingGateway {

    @Inject
    GivingService givingService;

    public String give(Giving givingRequest) throws NoSuchTicketException, ExpiredTransitionalTicketException, CustomerNotFoundException.NoSeatAvailableException {

        return givingService.give(givingRequest);
    }


}

