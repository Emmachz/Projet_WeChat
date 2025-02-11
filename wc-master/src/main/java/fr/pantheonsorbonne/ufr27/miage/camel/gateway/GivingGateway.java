package fr.pantheonsorbonne.ufr27.miage.camel.gateway;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import fr.pantheonsorbonne.ufr27.miage.exception.UnsuficientQuotaDonationException;
import fr.pantheonsorbonne.ufr27.miage.exception.UserNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.GivingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.CamelContext;

@ApplicationScoped
public class GivingGateway {

    @Inject
    GivingService givingService;


    public Giving giveMoney(Giving give) throws  UnsuficientQuotaDonationException {
        return givingService.giveMoney(give);
    }
    public Giving updateDonationAfterBank (Giving give) throws UnsuficientQuotaDonationException{
        return givingService.updateDonationAfterBank(give);
    }
    public Giving giveTime(Giving give) throws  UnsuficientQuotaDonationException {
        return givingService.giveTime(give);
    }
    public Giving giveClothe(Giving give) throws  UnsuficientQuotaDonationException {
        return givingService.giveClothe(give);
    }
    public Giving convertirGiving(Giving give) throws UserNotFoundException.NoExistUserException {
        return givingService.convertirGiving(give);
    }


}