package net.rainbowfurry.phoenixbot.events.core;

import java.util.ArrayList;
import java.util.List;

public class ChatEventManager {

    private final List<ChatEventListener> listeners = new ArrayList<>();

    public void addListener(ChatEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ChatEventListener listener) {
        listeners.remove(listener);
    }

    public void notifyUserJoin(String chatId) {
        for (ChatEventListener listener : listeners) {
            listener.onUserJoin(chatId);
        }
    }

    public void notifyUserLeave(String chatId) {
        for (ChatEventListener listener : listeners) {
            listener.onUserLeave(chatId);
        }
    }

}
