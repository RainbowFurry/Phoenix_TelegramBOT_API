package net.rainbowfurry.phoenixbot;

import net.rainbowfurry.phoenixbot.commands.*;
import net.rainbowfurry.phoenixbot.events.JoinLeaveListener;
import net.rainbowfurry.phoenixbot.events.core.ChatEventManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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
    private final ChatEventManager eventManager = new ChatEventManager();

    public PhoenixBot (){
        registerCommands();
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        // Check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

            System.out.println(update);

            // Command Handling
            System.out.println(commands.size());
            if(receivedText.contains("/")) {
                Command command = commands.get(receivedText.replace("/", "").replace("@" + getBotUsername(), ""));
                if (command != null) {
                    command.run(chatId, receivedText);
                } else {
                    sendMessageToUser(chatId, "Unknown command. Use /help for a list of available commands.");
                }
            }

            // Event Handling
            System.out.println("Test0");
            if (update.getMyChatMember() != null) {
                System.out.println("Test1");
                String newStatus = update.getMyChatMember().getNewChatMember().getStatus();
                System.out.println(newStatus);
                String oldStatus = update.getMyChatMember().getOldChatMember().getStatus();
                System.out.println(oldStatus);

                if ("member".equals(newStatus) && !"member".equals(oldStatus)) {
                    // User joined
                    eventManager.notifyUserJoin(chatId);
                } else if (!"member".equals(newStatus) && "member".equals(oldStatus)) {
                    // User left
                    eventManager.notifyUserLeave(chatId);
                }

            }

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

    public void run(String[] args){
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
            sendMessageToUser(CHAT_ID, "Bot is online!");

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
       //sendMessageToUser(CHAT_ID, "Hallo Hasi");
    }

    private void onShutDown(){
        sendMessageToUser(CHAT_ID, "Bot is offline!");
    }

    private void registerCommands() {
        commands.put("test", new TestCommand());
    }

    public void sendMessageToUser(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
