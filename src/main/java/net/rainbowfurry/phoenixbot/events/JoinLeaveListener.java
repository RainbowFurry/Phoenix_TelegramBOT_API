package net.rainbowfurry.phoenixbot.events;

import net.rainbowfurry.phoenixbot.events.core.ChatEventListener;

public class JoinLeaveListener implements ChatEventListener {
    @Override
    public void onUserJoin(String chatId) {
        System.out.println("Join chat: " + chatId);
    }

    @Override
    public void onUserLeave(String chatId) {
        System.out.println("Leave chat: " + chatId);
    }
}
