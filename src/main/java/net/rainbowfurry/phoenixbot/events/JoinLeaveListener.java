package net.rainbowfurry.phoenixbot.events;

import net.rainbowfurry.phoenixbot.events.core.EventListener;

public class JoinLeaveListener implements EventListener {
    @Override
    public void onUserJoin(String chatId) {
        System.out.println("Join chat: " + chatId);
    }

    @Override
    public void onUserLeave(String chatId) {
        System.out.println("Leave chat: " + chatId);
    }
}
