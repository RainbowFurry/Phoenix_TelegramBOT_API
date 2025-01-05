package net.rainbowfurry.phoenixtelegrambotapi;

import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class TextMessage {

    public static boolean sendMessage(SendMessage message){
        try {
            TelegramBot.bot.execute(message);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendMessage(String chatId, String text) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        //message.enableMarkdown(true);
        //message.enableMarkdownV2(true);
        message.enableHtml(true);

        try {
            TelegramBot.bot.execute(message);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendAudio(SendAudio sendAudio){

        try {
            TelegramBot.bot.execute(sendAudio);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendAudio(String chatId, String audio){

        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId(chatId);
        sendAudio.setAudio(getInpuFile(audio));

        try {
            TelegramBot.bot.execute(sendAudio);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendLocation(SendLocation sendLocation){

        try {
            TelegramBot.bot.execute(sendLocation);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendLocation(String chatId, double latidude, double longitude){

        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId(chatId);
        sendLocation.setLatitude(latidude);
        sendLocation.setLongitude(longitude);

        try {
            TelegramBot.bot.execute(sendLocation);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void stopLocation(){

        StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation();
        stopMessageLiveLocation.setChatId("1268155750");
        stopMessageLiveLocation.setMessageId(1268155750);

        try {
            TelegramBot.bot.execute(stopMessageLiveLocation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean sendContact(SendContact sendContact){

        try {
            TelegramBot.bot.execute(sendContact);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendContact(String chatId, String firstName, String lastName, String number){

        SendContact sendContact = new SendContact();
        sendContact.setChatId(chatId);
        sendContact.setFirstName(firstName);
        sendContact.setLastName(lastName);
        sendContact.setPhoneNumber(number);
        //sendContact.setProtectContent(true); //message forward not possible

        try {
            TelegramBot.bot.execute(sendContact);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendDocument(SendDocument sendDocument){

        try {
            TelegramBot.bot.execute(sendDocument);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendDocument(String chatId, String document){

        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(getInpuFile(document));

        try {
            TelegramBot.bot.execute(sendDocument);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean sendSticker(SendSticker sendSticker){

        try {
            TelegramBot.bot.execute(sendSticker);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendSticker(String chatId, String sticker){

        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId(chatId);
        sendSticker.setSticker(getInpuFile(sticker));

        try {
            TelegramBot.bot.execute(sendSticker);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendPhoto(SendPhoto sendPhoto){

        try {
            TelegramBot.bot.execute(sendPhoto);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendPhoto(String chatId, String image){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId);
        sendPhoto.setPhoto(getInpuFile(image));

        try {
            TelegramBot.bot.execute(sendPhoto);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVideo(SendVideo sendVideo){

        try {
            TelegramBot.bot.execute(sendVideo);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVideo(String chatId, String video){
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId(chatId);
        sendVideo.setVideo(getInpuFile(video));

        try {
            TelegramBot.bot.execute(sendVideo);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVoice(SendVoice sendVoice){

        try {
            TelegramBot.bot.execute(sendVoice);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendVoice(String chatId, String voice){

        SendVoice sendVoice = new SendVoice();
        sendVoice.setChatId(chatId);
        sendVoice.setVoice(getInpuFile(voice));

        try {
            TelegramBot.bot.execute(sendVoice);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }


    private static InputFile getInpuFile(String inputFile){
        if(inputFile.contains("http") || inputFile.contains("."))
            return new InputFile(new File(inputFile));
        else
            return new InputFile(inputFile);
    }


    public static void deleteMessage(long chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        try {
            TelegramBot.bot.execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void editMesssage(String chatId, Integer messageId, String text){

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(text);

        try {
            TelegramBot.bot.execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void replyOnMessage(String chatId, String text, int replyToMessageId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyToMessageId(replyToMessageId);

        try {
            TelegramBot.bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void forwardMessage(String chatId, String text, int messageId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setMessageThreadId(messageId);

        try {
            TelegramBot.bot.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void pinMessage(){
        //ToDo
    }
    
}
