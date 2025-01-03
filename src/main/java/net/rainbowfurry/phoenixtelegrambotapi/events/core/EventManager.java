package net.rainbowfurry.phoenixtelegrambotapi.events.core;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    private void executeEvents(String chatId) {
        for (EventListener listener : listeners) {
            listener.onUserJoin(chatId);
            listener.onUserLeave(chatId);
        }
    }

    public void notifyUserJoin(String chatId) {
        for (EventListener listener : listeners) {
            listener.onUserJoin(chatId);
        }
    }

    public void notifyUserLeave(String chatId) {
        for (EventListener listener : listeners) {
            listener.onUserLeave(chatId);
        }
    }

}
