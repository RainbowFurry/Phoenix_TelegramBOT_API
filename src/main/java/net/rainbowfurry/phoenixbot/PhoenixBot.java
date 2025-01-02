package net.rainbowfurry.phoenixbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rainbowfurry.phoenixbot.commands.*;
import net.rainbowfurry.phoenixbot.events.core.EventManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
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
import java.util.logging.Logger;

public class PhoenixBot extends TelegramLongPollingBot {

    public static PhoenixBot instance;
    private final String BOT_TOKEN = "";
    private final String BOT_NAME = "";
    private final String CHAT_ID = "";
    private final String clientID = "";

    private final Map<String, Command> commands = new HashMap<>();
    private final EventManager eventManager = new EventManager();

    public PhoenixBot(){
        registerCommands();
    }

    @Override
    public void onUpdateReceived(Update update) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

//        try {
//            System.out.println(gson.toJson(getMe()));
//            System.out.println(getOptions().getBaseUrl());
//            System.out.println(getBaseUrl());
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }

        //System.out.println(update);
        String _update = gson.toJson(update);
        System.out.println(_update);

        Message message = update.getMessage();
        String _message = gson.toJson(message);
        //System.out.println(_message);

        //message.get
        //update.get

        String _chatmember = gson.toJson(update.getMyChatMember());
        //System.out.println(_chatmember);

        String _chat = gson.toJson(update.getMessage().getChat());
        //System.out.println(_chat);//

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

    public static void main(String[] args) {
        try {
            instance = new PhoenixBot();
            instance.run(args);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void run(String[] args){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new PhoenixBot());

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                onShutDown();
            }));

            Logger logger = Logger.getGlobal();
            System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
            logger.info("Test");

            // Register Commands
            registerCommands();

            // Register Listener
            //getEventManager().addListener(new JoinLeaveListener());

            //sendMessageToUser(clientID, "Hey Hey!");
            //sendMessageToUser(CHAT_ID, "Bot is online!");
sendVoice("sa");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
       //sendMessageToUser(CHAT_ID, "Hallo Hasi");
        //sendMessage("1345362239", "Hallo Mama, das ist übrigens ein BOT!");
    }

    private void onShutDown(){
        //sendMessageToUser(CHAT_ID, "Bot is offline!");
    }

    private void registerCommands() {
        commands.put("test", new TestCommand());
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

    private void sendAudio(){
        SendAudio sendAudio = new SendAudio();
    }

    private void sendLocation(){
        SendLocation sendLocation = new SendLocation();
    }

    private void sendContact(){
        SendContact sendContact = new SendContact();
    }

    private void sendDocument(){
        SendDocument sendDocument = new SendDocument();
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

//    SetChatMenuButton setChatMenuButton = new SetChatMenuButton();
//        setChatMenuButton.setMenuButton(new MenuButton() {
//        @Override
//        public String getType() {
//            return "Test";
//        }
//    });

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

    public void pinMessage(String chatId, int messageId) {
        PinChatMessage pinRequest = new PinChatMessage();
        pinRequest.setChatId(chatId);
        pinRequest.setMessageId(messageId);

        try {
            execute(pinRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //reactOnMessage - does not exist?! no response so far
    // pin message

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
