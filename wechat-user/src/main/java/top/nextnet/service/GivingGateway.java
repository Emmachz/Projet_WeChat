package top.nextnet.service;

public interface BookingGateway {
    void sendBookingOrder(int standingCount, int seatingCount, int venueId);
}
