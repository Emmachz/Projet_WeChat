package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.service.GivingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GiveGateway {

    @Inject
    GivingService givingService;

    public Giving give(Giving givingRequest) throws UnsuficientQuotaForVenueException {

        return givingService.give(givingRequest);
    }


}

