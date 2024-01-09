package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Booking;
import fr.pantheonsorbonne.ufr27.miage.dto.Giving;

public interface UserInterface {
    void showErrorMessage(String errorMessage);

    void showSuccessMessage(String s);

    String getCustomerFirstName();

    String getCustomerLastName();

    String getCustomerEmail();

    Giving getDonationFromOperator();
}
