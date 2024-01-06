package top.nextnet.service;

public interface PurshasingGateway {
    void sendWeChatPurshasing(int idWeChatCustomer, int idWeChatExternalSeller, int amount);
}
