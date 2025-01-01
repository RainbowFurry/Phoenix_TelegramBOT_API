package net.rainbowfurry.phoenixbot;

import net.rainbowfurry.phoenixbot.commands.Command;
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

public class TelegramBot extends TelegramLongPollingBot {

    private String BOT_TOKEN = "";
    private String BOT_NAME = "";

    private final Map<String, Command> commands = new HashMap<>();
    private final ChatEventManager eventManager = new ChatEventManager();

    public TelegramBot(String token, String botName) {
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

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void onShutDown(){

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
            if (receivedText.contains("/")) {
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
