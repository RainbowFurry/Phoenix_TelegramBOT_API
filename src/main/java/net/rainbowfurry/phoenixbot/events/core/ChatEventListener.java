package net.rainbowfurry.phoenixbot.events.core;

public interface ChatEventListener {
    void onUserJoin(String chatId);
    void onUserLeave(String chatId);
}
