package top.nextnet.cli;

import fr.pantheonsorbonne.ufr27.miage.dto.Giving;
import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface UserInterfaceDonation extends BiConsumer<TextIO, RunnerData>, UserInterface {

    void displayAvailableDonations();

}
