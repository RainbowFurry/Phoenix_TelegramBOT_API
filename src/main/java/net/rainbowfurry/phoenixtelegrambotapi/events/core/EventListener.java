package net.rainbowfurry.phoenixtelegrambotapi.events.core;

import org.telegram.telegrambots.meta.api.objects.*;

public interface EventListener {

    default void onUpdate(Update update){}
    default void onChannelUpdate(Update update){}
    default void onCallback(CallbackQuery callbackQuery){}
    default void onChannelCallback(CallbackQuery callbackQuery){}
    default void onCommand(Message command){}
    default void onChannelCommand(Message message){}
    default void onMessage(Message message){}
    default void onChannelMessage(Message message){}

    default void onAudioMessage(Message message){}
    default void onChannelAudioMessage(Message message){}
    default void onLocationMessage(Message message){}
    default void onChannelLocationMessage(Message message){}
    default void onContactMessage(Message message){}
    default void onChannelContactMessage(Message message){}
    default void onDocumentMessage(Message message){}
    default void onChannelDocumentMessage(Message message){}
    default void onStickerMessage(Message message){}
    default void onChannelStickerMessage(Message message){}
    default void onPhotoMessage(Message message){}
    default void onChannelPhotoMessage(Message message){}
    default void onVideoMessage(Message message){}
    default void onChannelVideoMessage(Message message){}
    default void onVoiceMessage(Message message){}
    default void onChannelVoiceMessage(Message message){}

    default void onMessageEdit(Message message){}
    default void onChannelMessageEdit(Message message){}
    //ToDo
    default void onMessageReply(Message message){}
    default void onChannelMessageReply(Message message){}
    default void onMessageShareForward(Message message){}
    default void onChannelMessageShareForward(Message message){}
    default void onMessageDelete(Message message){}
    default void onChannelMessageDelete(Message message){}
    default void onMessagePin(Message message){}
    default void onChannelMessagePin(Message message){}
    //default void onMessageReact(){}//nicht möglich?

    default void onJoin(User user, Chat chat){}
    default void onChannelJoin(User user, Chat chat){}
    default void onLeave(User user, Chat chat){}
    default void onChannelLeave(User user, Chat chat){}

}
