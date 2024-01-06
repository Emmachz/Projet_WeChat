package top.nextnet.cli;

import org.beryx.textio.TextIO;

import java.util.function.BiConsumer;

public interface UserInterfaceCLI extends BiConsumer<TextIO, RunnerData>, UserInterface {

    void displayAvailableGigsToCli();
}
