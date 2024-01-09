package top.nextnet.service;

public interface GivingGateway {
    void sendGivingOrder(int donationId, String region, String typeGive, int quantity);
}
