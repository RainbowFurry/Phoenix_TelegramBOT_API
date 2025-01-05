package net.rainbowfurry.phoenixtelegrambotapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.rainbowfurry.phoenixtelegrambotapi.commands.BotInfoCommand;
import net.rainbowfurry.phoenixtelegrambotapi.commands.Command;
import net.rainbowfurry.phoenixtelegrambotapi.commands.HelpCommand;
import net.rainbowfurry.phoenixtelegrambotapi.commands.TestCommand;
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
    public static Map<String, Command> channelCommands = new HashMap<>();
    public static final EventManager eventManager = new EventManager();

    public TelegramBot(String token, String botName) {
        this.bot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;
        initBot();
    }

    private TelegramBot(String token, String botName, boolean v1) {
        this.bot = this;
        this.BOT_NAME = botName;
        this.BOT_TOKEN = token;

        // Register Commands
        commands.put("help", new HelpCommand());
        commands.put("botinfo", new BotInfoCommand());
        commands.put("test", new TestCommand());

        if(channelCommands.isEmpty())
            channelCommands = commands;

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

    private void onShutDown() {

    }

    @Override
    public void onUpdateReceived(Update update) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String _update = gson.toJson(update);
        System.out.println(_update);

        Message message = update.getMessage();
        String _message = gson.toJson(message);
        //System.out.println(_message);

        String _chatmember = gson.toJson(update.getMyChatMember());
        //System.out.println(_chatmember);


        if(!update.getMessage().getChat().getId().toString().startsWith("-")) {
            // Update
            eventManager.onUpdate(update);

            // Audio
            if (update.getMessage().getAudio() != null) {
                eventManager.onAudioMessage(update.getMessage());
            }

            // Location
            if (update.getMessage().getLocation() != null) {
                eventManager.onLocationMessage(update.getMessage());
            }

            // Contact
            if (update.getMessage().getContact() != null) {
                eventManager.onContactMessage(update.getMessage());
            }

            // Document
            if (update.getMessage().getDocument() != null) {
                eventManager.onDocumentMessage(update.getMessage());
            }

            // Sticker
            if (update.getMessage().getSticker() != null) {
                eventManager.onStickerMessage(update.getMessage());
            }

            // Photo
            if (update.getMessage().getPhoto() != null) {
                eventManager.onPhotoMessage(update.getMessage());
            }

            // Video
            if (update.getMessage().getVideo() != null) {
                eventManager.onVideoMessage(update.getMessage());
            }

            // Voice
            if (update.getMessage().getVoice() != null) {
                eventManager.onVoiceMessage(update.getMessage());
            }

            // Edit Message
            if (update.getEditedMessage() != null) {
                eventManager.onEditMessage(update.getEditedMessage());
            }

            // CallBack
            if (update.getCallbackQuery() != null) {
                eventManager.onCallBack(update.getCallbackQuery());
            }

            // Command
            if (update.hasMessage() && update.getMessage().getText() != null) {

                String chatId = update.getMessage().getChatId().toString();
                String receivedText = update.getMessage().getText();

                if (receivedText.startsWith("/")) {
                    Command command = commands.get(receivedText.replace("/", "").replace("@" + getBotUsername(), ""));
                    if (command != null) {
                        command.command(chatId, receivedText);
                        eventManager.onCommand(update.getMessage());
                    } else {
                        TextMessage.sendMessage(chatId, "Unknown command. Use /help for a list of available commands.");
                    }
                } else {
                    eventManager.onMessage(update.getMessage());
                }
            }

        }else{

            if (update.hasMessage() && update.getMessage().getText() != null) {
                String chatId = update.getMessage().getChatId().toString();
                String receivedText = update.getMessage().getText();

                if (receivedText.startsWith("/")) {
                    Command command = channelCommands.get(receivedText.replace("/", "").replace("@" + getBotUsername(), ""));
                    if (command != null) {
                        command.command(chatId, receivedText);
                        eventManager.onChannelCommand(update.getMessage());
                    } else {
                        TextMessage.sendMessage(chatId, "Unknown command. Use /help for a list of available commands.");
                    }
                } else {
                    eventManager.onChannelMessage(update.getMessage());
                }
            }

            // Update
            eventManager.onChannelUpdate(update);

            // Audio
            if (update.getMessage().getAudio() != null) {
                eventManager.onChannelAudioMessage(update.getMessage());
            }

            // Location
            if (update.getMessage().getLocation() != null) {
                eventManager.onChannelLocationMessage(update.getMessage());
            }

            // Contact
            if (update.getMessage().getContact() != null) {
                eventManager.onChannelContactMessage(update.getMessage());
            }

            // Document
            if (update.getMessage().getDocument() != null) {
                eventManager.onChannelDocumentMessage(update.getMessage());
            }

            // Sticker
            if (update.getMessage().getSticker() != null) {
                eventManager.onChannelStickerMessage(update.getMessage());
            }

            // Photo
            if (update.getMessage().getPhoto() != null) {
                eventManager.onChannelPhotoMessage(update.getMessage());
            }

            // Video
            if (update.getMessage().getVideo() != null) {
                eventManager.onChannelVideoMessage(update.getMessage());
            }

            // Voice
            if (update.getMessage().getVoice() != null) {
                eventManager.onChannelVoiceMessage(update.getMessage());
            }

            // Join Leave
            if (update.getMyChatMember() != null) {
                String newStatus = update.getMyChatMember().getNewChatMember().getStatus();
                String oldStatus = update.getMyChatMember().getOldChatMember().getStatus();

                // Check if the bot was added to a channel
                if ("administrator".equals(newStatus) || "member".equals(newStatus)) {
                    System.out.println("Bot added to channel: " + update.getMessage().getChat().getTitle());
                }

                // Check if the bot was removed from a channel
                if ("kicked".equals(newStatus) || "left".equals(newStatus)) {
                    System.out.println("Bot removed from channel: " + update.getMessage().getChat().getTitle());
                }

                if (newStatus != null) {
                    // User joined
                    eventManager.onChannelJoin(update.getMessage().getFrom(), update.getMessage().getChat());
                } else if (oldStatus != null) {
                    // User left
                    eventManager.onChannelLeave(update.getMessage().getFrom(), update.getMessage().getChat());
                }
            }

            // Edit Message
            if (update.getEditedMessage() != null) {
                eventManager.onChannelEditMessage(update.getEditedMessage());
            }

            // CallBack
            if(update.getCallbackQuery() != null){
                eventManager.onChannelCallBack(update.getCallbackQuery());
            }

        }

        // Join Leave
        if (update.getMyChatMember() != null) {
            String newStatus = update.getMyChatMember().getNewChatMember().getStatus();
            String oldStatus = update.getMyChatMember().getOldChatMember().getStatus();

            if (newStatus != null) {
                // User joined
                eventManager.onJoin(update.getMessage().getFrom(), update.getMessage().getChat());
            } else if (oldStatus != null) {
                // User left
                eventManager.onLeave(update.getMessage().getFrom(), update.getMessage().getChat());
            }
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
