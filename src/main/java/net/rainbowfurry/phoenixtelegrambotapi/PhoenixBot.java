package net.rainbowfurry.phoenixtelegrambotapi;

import net.rainbowfurry.phoenixtelegrambotapi.commands.*;
import net.rainbowfurry.phoenixtelegrambotapi.events.core.EventManager;
import net.rainbowfurry.phoenixtelegrambotapi.manager.ConfigManager;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PhoenixBot {

    public static PhoenixBot instance;
    public static TelegramBot telegramBot;
    private ConfigManager configManager;

    private final Map<String, Command> commands = new HashMap<>();
    private final EventManager eventManager = new EventManager();

    public static void main(String[] args) {
        try {
            instance = new PhoenixBot();
            instance.run(args);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    private void run(String[] args){
        //try {

//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new PhoenixBot());

        // Load Config Manager
        configManager = new ConfigManager();

        // Init Telegram Bot
        if(configManager.getKey("token") != null && !configManager.getKey("token").equals("YOUR_TOKEN")) {
            telegramBot = new TelegramBot(configManager.getKey("token"), configManager.getKey("name"));
        } else {
            System.out.println("No Bot-Token set up!");
            System.exit(0);
        }

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

//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
       //sendMessageToUser("878502184", "Hallo Hasi");
        //sendMessage("1345362239", "Hallo Mama, das ist übrigens ein BOT!");
    }

    private void onShutDown(){
        //sendMessageToUser(CHAT_ID, "Bot is offline!");
    }

    private void registerCommands() {
        commands.put("test", new TestCommand());
    }

}
