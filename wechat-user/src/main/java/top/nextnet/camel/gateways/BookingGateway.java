package top.nextnet.camel.gateways;

public interface BookingGateway {
    void sendBookingOrder(int standingCount, int seatingCount, int venueId);
}
