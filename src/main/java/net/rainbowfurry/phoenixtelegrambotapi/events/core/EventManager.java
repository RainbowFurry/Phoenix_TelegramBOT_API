package net.rainbowfurry.phoenixtelegrambotapi.events.core;

import org.telegram.telegrambots.meta.api.objects.*;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private final List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        listeners.add(listener);
    }


    public void onUpdate(Update update){
        for (EventListener listener : listeners) {
            listener.onUpdate(update);
        }
    }

    public void onChannelUpdate(Update update){
        for (EventListener listener : listeners) {
            listener.onChannelUpdate(update);
        }
    }

    public void onCommand(Message message){
        for (EventListener listener : listeners) {
            listener.onCommand(message);
        }
    }

    public void onChannelCommand(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelCommand(message);
        }
    }

    public void onMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onMessage(message);
        }
    }

    public void onChannelMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelMessage(message);
        }
    }


    public void onAudioMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onAudioMessage(message);
        }
    }

    public void onChannelAudioMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelAudioMessage(message);
        }
    }

    public void onLocationMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onLocationMessage(message);
        }
    }

    public void onChannelLocationMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelLocationMessage(message);
        }
    }

    public void onContactMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onContactMessage(message);
        }
    }

    public void onChannelContactMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelContactMessage(message);
        }
    }

    public void onDocumentMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onDocumentMessage(message);
        }
    }

    public void onChannelDocumentMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelDocumentMessage(message);
        }
    }

    public void onStickerMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onStickerMessage(message);
        }
    }

    public void onChannelStickerMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelStickerMessage(message);
        }
    }

    public void onPhotoMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onPhotoMessage(message);
        }
    }

    public void onChannelPhotoMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelPhotoMessage(message);
        }
    }

    public void onVideoMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onVideoMessage(message);
        }
    }

    public void onChannelVideoMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelVideoMessage(message);
        }
    }

    public void onVoiceMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onVoiceMessage(message);
        }
    }

    public void onChannelVoiceMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelVoiceMessage(message);
        }
    }


    public void onEditMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onMessageEdit(message);
        }
    }

    public void onChannelEditMessage(Message message){
        for (EventListener listener : listeners) {
            listener.onChannelMessageEdit(message);
        }
    }


    public void onJoin(User user, Chat chat) {
        for (EventListener listener : listeners) {
            listener.onJoin(user, chat);
        }
    }

    public void onChannelJoin(User user, Chat chat) {
        for (EventListener listener : listeners) {
            listener.onChannelJoin(user, chat);
        }
    }

    public void onLeave(User user, Chat chat) {
        for (EventListener listener : listeners) {
            listener.onLeave(user, chat);
        }
    }

    public void onChannelLeave(User user, Chat chat) {
        for (EventListener listener : listeners) {
            listener.onChannelLeave(user, chat);
        }
    }

    public void onCallBack(CallbackQuery callbackQuery){
        for (EventListener listener : listeners) {
            listener.onCallback(callbackQuery);
        }
    }

    public void onChannelCallBack(CallbackQuery callbackQuery){
        for (EventListener listener : listeners) {
            listener.onChannelCallback(callbackQuery);
        }
    }

}
