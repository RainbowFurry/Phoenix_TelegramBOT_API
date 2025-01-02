package net.rainbowfurry.phoenixbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rainbowfurry.phoenixbot.commands.Command;
import net.rainbowfurry.phoenixbot.events.core.EventManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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

    private void initBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(BOT_TOKEN, BOT_NAME));

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


    public void sendMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /* send:
     * Audio
     * Location
     * Contact
     * Document
     * Sticker
     * Photo
     * Video
     * Voice
     * */

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
