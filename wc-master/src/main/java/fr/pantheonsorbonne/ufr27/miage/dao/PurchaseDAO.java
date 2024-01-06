package fr.pantheonsorbonne.ufr27.miage.dao;

public interface PurchaseDAO {
    public void createPurchase(int idES, int idWC, int amount);

    public void confirmPurchase(int id);
}
