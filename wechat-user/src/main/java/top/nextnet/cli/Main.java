package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.inject.Inject;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.swing.SwingTextTerminal;
import picocli.CommandLine.Command;
import top.nextnet.service.BookingGateway;
import top.nextnet.service.GivingGateway;


@Command(name = "greeting", mixinStandardHelpOptions = true)
public class Main implements Runnable {


    @Inject
    UserInterfaceDonation eCommerce;

    @Inject
    GivingGateway givingGateway;

    @Override
    public void run() {

        System.setProperty(TextIoFactory.TEXT_TERMINAL_CLASS_PROPERTY, SwingTextTerminal.class.getName());
        TextIO textIO = TextIoFactory.getTextIO();
        var terminal = TextIoFactory.getTextTerminal();

        eCommerce.accept(textIO, new RunnerData(""));


        while (true) {
            try {
                eCommerce.displayAvailableDonations();
                Giving giving = eCommerce.getDonationFromOperator();
                givingGateway.sendGivingOrder(giving.getDonationId(), giving.getuserRegion(), giving.getTypeGive(), giving.getQuantity());
            } catch (Exception e) {
                eCommerce.showErrorMessage(e.getMessage());
            }
        }


    }

}
