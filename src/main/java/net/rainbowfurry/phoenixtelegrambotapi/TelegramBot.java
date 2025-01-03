package net.rainbowfurry.phoenixtelegrambotapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rainbowfurry.phoenixtelegrambotapi.commands.Command;
import net.rainbowfurry.phoenixtelegrambotapi.events.core.EventManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.StopMessageLiveLocation;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot telegramBot;
    private String BOT_TOKEN = "";
    private String BOT_NAME = "";

    private final Map<String, Command> commands = new HashMap<>();
    private final EventManager eventManager = new EventManager();

    public TelegramBot(String token, String botName) {
        this.telegramBot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;
        initBot();
    }

    private TelegramBot(String token, String botName, boolean v1){
        this.telegramBot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;
    }

    private void initBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            //botsApi.registerBot(new TelegramBot());
            botsApi.registerBot(new TelegramBot(BOT_TOKEN, BOT_NAME, false));

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                onShutDown();
            }));

//            SetMyCommands setMyCommands = new SetMyCommands();
//            setMyCommands.setCommands();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public TelegramBot registerCommand(String commandName, Command command){
        commands.put(commandName, command);
        return this;
    }

    private void onShutDown(){

    }

    @Override
    public void onUpdateReceived(Update update) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String _update = gson.toJson(update);
        //System.out.println(_update);

        Message message = update.getMessage();
        String _message = gson.toJson(message);
        //System.out.println(_message);

        String _chatmember = gson.toJson(update.getMyChatMember());
        System.out.println(_chatmember);

        // Check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

            // Command Handling
            if(receivedText.contains("/")) {
                Command command = commands.get(receivedText.replace("/", "").replace("@" + getBotUsername(), ""));
                if (command != null) {
                    command.command(chatId, receivedText);
                } else {
                    sendMessage(chatId, "Unknown command. Use /help for a list of available commands.");
                }
            }

            // Event Handling
//            if (update.getMyChatMember() != null) {
//                String newStatus = update.getMyChatMember().getNewChatMember().getStatus();
//                String oldStatus = update.getMyChatMember().getOldChatMember().getStatus();
//
//                if ("member".equals(newStatus) && !"member".equals(oldStatus)) {
//                    // User joined
//                    eventManager.notifyUserJoin(chatId);
//                } else if (!"member".equals(newStatus) && "member".equals(oldStatus)) {
//                    // User left
//                    eventManager.notifyUserLeave(chatId);
//                }
//            }

            System.out.println(chatId + " " + receivedText);
        }
    }


    public boolean sendMessage(SendMessage message){
        try {
            execute(message);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sendMessage(String chatId, String text) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
            return true;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendAudio(String audio){

        SendAudio sendAudio = new SendAudio();
        sendAudio.setChatId("1268155750");
        sendAudio.setAudio(getInpuFile(audio));

        try {
            execute(sendAudio);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendLocation(){

        SendLocation sendLocation = new SendLocation();
        sendLocation.setChatId("1268155750");
        sendLocation.setLatitude(51.001849);
        sendLocation.setLongitude(7.562811);

        try {
            execute(sendLocation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void stopLocation(){

        StopMessageLiveLocation stopMessageLiveLocation = new StopMessageLiveLocation();
        stopMessageLiveLocation.setChatId("1268155750");
        stopMessageLiveLocation.setMessageId(1268155750);

        try {
            execute(stopMessageLiveLocation);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendContact(){

        SendContact sendContact = new SendContact();
        sendContact.setChatId("1268155750");
        sendContact.setFirstName("Jasmin");
        sendContact.setLastName("Hoffmann");
        sendContact.setPhoneNumber("+49 Test");
        //sendContact.setProtectContent(true); //message forward not possible

        try {
            execute(sendContact);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendDocument(String document){

        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId("1268155750");
        sendDocument.setDocument(getInpuFile(document));

        try {
            execute(sendDocument);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendSticker(){
        SendSticker sendSticker = new SendSticker();
        sendSticker.setChatId("1268155750");
        sendSticker.setSticker(new InputFile("CAACAgIAAxkBAAOuZ3bMU8Im_wswcDDcxGKxqfuxRksAAio_AAL7vFFJUIbLPmCGmDs2BA"));

        try {
            execute(sendSticker);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendPhoto(String image){

        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId("1268155750");

        if(image.contains("http"))
            sendPhoto.setPhoto(new InputFile("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Cat03.jpg/1024px-Cat03.jpg"));
        else
            sendPhoto.setPhoto(new InputFile(new File("C:\\Users\\Jasmin\\Videos\\HeimServer\\1735326538301ar4c1zk5.jpg")));

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendVideo(String video){
        SendVideo sendVideo = new SendVideo();
        sendVideo.setChatId("1268155750");

        if(video.contains("http"))
            sendVideo.setVideo(new InputFile(new File(video)));
        else
            sendVideo.setVideo(new InputFile(video));

        try {
            execute(sendVideo);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendVoice(String voice){

        SendVoice sendVoice = new SendVoice();
        sendVoice.setChatId("1268155750");
        sendVoice.setVoice(getInpuFile(voice));

        if(voice.contains(".ogg"))
            sendVoice.setVoice(new InputFile(new File(voice)));
        else
            sendVoice.setVoice(new InputFile("AwACAgIAAxkBAAOyZ3bSA5fecTbON4b_GUn8VRLcDnIAAptlAAJjoblLH1PPR9vgHZo2BA"));

        try {
            execute(sendVoice);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void execute(Method method){
        execute(method);
    }

    private void executeAsync(Method method){
        executeAsync(method);
    }

    private InputFile getInpuFile(String inputFile){
        if(inputFile.contains("http") || inputFile.contains("."))
            return new InputFile(new File(inputFile));
        else
            return new InputFile(inputFile);
    }


    public void deleteMessage(long chatId, int messageId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(messageId);

        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMesssage(String chatId, Integer messageId, String text){

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(messageId);
        editMessageText.setText(text);

        try {
            execute(editMessageText);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void replyOnMessage(String chatId, String text, int replyToMessageId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setReplyToMessageId(replyToMessageId);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void forwardMessage(String chatId, String text, int messageId){
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        message.setMessageThreadId(messageId);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //reactOnMessage

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
