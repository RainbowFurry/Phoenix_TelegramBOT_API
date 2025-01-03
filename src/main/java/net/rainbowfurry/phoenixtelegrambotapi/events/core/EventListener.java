package net.rainbowfurry.phoenixtelegrambotapi.events.core;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface EventListener {

    default void onUpdate(Update update){}
    default void onCommand(String command){}
    default void onMessage(Message message){}
    default void onChannelMessage(Message message){}

    default void onAudioMessage(){}
    default void onLocationMessage(){}
    default void onContactMessage(){}
    default void onDocumentMessage(){}
    default void onStickerMessage(){}
    default void onPhotoMessage(){}
    default void onVideoMessage(){}
    default void onVoiceMessage(){}

    default void onMessageEdit(){}
    default void onMessageReply(){}
    default void onMessageShareForward(){}
    default void onMessageDelete(){}
    default void onMessagePin(){}
    //default void onMessageReact(){}//nicht möglich?

    default void onUserJoin(String chatId){}
    default void onUserLeave(String chatId){}
    default void onBotJoinChannel(){}
    default void onBotLeaveChannel(){}

}
