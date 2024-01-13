package fr.pantheonsorbonne.ufr27.miage.camel;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
<<<<<<< HEAD
=======
import fr.pantheonsorbonne.ufr27.miage.exception.QuantityGivenHigherThanDemandedException;
>>>>>>> a4244aa7616f8a31d39ca3cf452225aea48efc06
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaForVenueException;
import fr.pantheonsorbonne.ufr27.miage.model.Alert;
import fr.pantheonsorbonne.ufr27.miage.service.AlertService;
import fr.pantheonsorbonne.ufr27.miage.service.GivingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

import java.io.IOException;

@ApplicationScoped
public class GivingGateway {

    @Inject
    GivingService givingService;

    @Inject
    CamelContext camelContext;


<<<<<<< HEAD
    public Giving giveMoney(Giving give) throws  UnsuficientQuotaForVenueException {
        return givingService.giveMoney(give);
    }
    public Giving giveTime(Giving give) throws  UnsuficientQuotaForVenueException {
        return givingService.giveTime(give);
    }
    public Giving giveClothe(Giving give) throws  UnsuficientQuotaForVenueException {
=======
    public Giving giveMoney(Giving give) throws QuantityGivenHigherThanDemandedException, UnsuficientQuotaForVenueException {
        return givingService.giveMoney(give);
    }
    public Giving giveTime(Giving give) throws QuantityGivenHigherThanDemandedException, UnsuficientQuotaForVenueException {
        return givingService.giveTime(give);
    }
    public Giving giveClothe(Giving give) throws QuantityGivenHigherThanDemandedException, UnsuficientQuotaForVenueException {
>>>>>>> a4244aa7616f8a31d39ca3cf452225aea48efc06
        return givingService.giveClothe(give);
    }

}