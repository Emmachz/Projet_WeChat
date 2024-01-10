package fr.pantheonsorbonne.ufr27.miage.services;

public interface AccountService {
    void creditMoney(int accountId, int amount);

    void debitMoney(int accountId, int amount);
}
