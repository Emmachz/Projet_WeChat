package top.nextnet.camel.gateways;

public interface GivingGateway {
    void sendGivingOrder(int donationId, String region, String typeGive, int quantity);
}
