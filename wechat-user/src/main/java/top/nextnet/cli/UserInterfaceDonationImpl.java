package top.nextnet.cli;


import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Donation;
import fr.pantheonsorbonne.ufr27.miage.dto.Gig;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import top.nextnet.resource.VendorService;
import top.nextnet.resource.UserService;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;


@ApplicationScoped
public class UserInterfaceDonationImpl implements UserInterfaceDonation {

    @Inject
    @RestClient
    VendorService vendorService;

    @Inject
    @RestClient
    UserService userService;


    TextTerminal<?> terminal;
    TextIO textIO;

    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userRegion")
    String userRegion;
    @ConfigProperty(name = "fr.pantheonsorbonne.ufr27.miage.userId")
    Integer userId;

    public void displayAvailableDonations(){
        terminal.println("your region : "+userRegion);
        for (Donation donation : userService.getDonations(userRegion)) {
            terminal.println("[" + donation.getDonationId() + "] " + donation.getDescription()+ " " + donation.getRequires() );
        }
    }

    public Giving getDonationFromOperator(){
        terminal.println("Do you want to help by giving a Donation ?");

        Integer donationId = textIO.newIntInputReader().withPossibleValues(userService.getDonations(userRegion).stream().map(g -> g.getDonationId()).collect(Collectors.toList())).read("Which Donation?");
        String typeGive = textIO.newStringInputReader().read("What do you want to give ?");
        Integer quantity = textIO.newIntInputReader().read("How many do you want to give?");

        return new Giving(donationId,this.userRegion,typeGive,quantity);
    }

    @Override
    public void accept(TextIO textIO, RunnerData runnerData) {
        this.textIO = textIO;
        terminal = textIO.getTextTerminal();
   }

    @Override
    public void showErrorMessage(String errorMessage) {
        terminal.getProperties().setPromptColor(Color.RED);
        terminal.println(errorMessage);
        terminal.getProperties().setPromptColor(Color.white);
    }

    @Override
    public void showSuccessMessage(String s) {
        terminal.getProperties().setPromptColor(Color.GREEN);
        terminal.println(s);
        terminal.getProperties().setPromptColor(Color.white);
    }


    @Override
    public String getCustomerFirstName() {
        return this.textIO.newStringInputReader().read("Customer First Name");

    }

    @Override
    public String getCustomerLastName() {
        return this.textIO.newStringInputReader().read("Customer Last Name");

    }

    @Override
    public String getCustomerEmail() {
        return this.textIO.newStringInputReader().read("Customer Email");

    }


}
