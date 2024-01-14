package top.nextnet.service;

public interface PurshasingGateway {
    void sendWeChatPurshasing(String weChatUser, String externalSeller, double amount);
}
