package net.rainbowfurry.phoenixtelegrambotapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rainbowfurry.phoenixtelegrambotapi.commands.Command;
import net.rainbowfurry.phoenixtelegrambotapi.events.core.EventManager;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {

    public static TelegramBot bot;
    private String BOT_TOKEN = "";
    private String BOT_NAME = "";

    public static Map<String, Command> commands = new HashMap<>();
    private final EventManager eventManager = new EventManager();

    public TelegramBot(String token, String botName) {
        this.bot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;
        initBot();
    }

    private TelegramBot(String token, String botName, boolean v1){
        this.bot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;
        //commands.put("wda", new TestCommand());
    }

    private void initBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot(BOT_TOKEN, BOT_NAME, false));

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

            // Command
            System.out.println(commands.size());
            if(receivedText.startsWith("/")) {
                Command command = commands.get(receivedText.replace("/", "").replace("@" + getBotUsername(), ""));
                if (command != null) {
                    command.command(chatId, receivedText);
                } else {
                    BotMessage.sendMessage(chatId, "Unknown command. Use /help for a list of available commands.");
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

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
