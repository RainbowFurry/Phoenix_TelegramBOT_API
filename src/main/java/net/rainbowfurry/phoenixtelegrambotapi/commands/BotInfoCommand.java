package net.rainbowfurry.phoenixtelegrambotapi.commands;

import net.rainbowfurry.phoenixtelegrambotapi.TextMessage;
import net.rainbowfurry.phoenixtelegrambotapi.TextFormatter;

public class BotInfoCommand implements Command{
    @Override
    public String getCommandDescription() {
        return "Shows Info about the Bot";
    }

    @Override
    public void command(String channelID, String message) {

        String _message = "The Phoenix Telegram Bot is made by @RainbowFurry and running with Java.\n" +
                "It is made with oure own API using the Telegram API\n" +
                "https://docs.rainbowfurry.com/phoenix-telegram-bot-api";

        TextMessage.sendMessage(channelID, _message);
        TextMessage.sendMessage(channelID, TextFormatter.codePre("TelegramBot telegramBot = new TelegramBot(BOT_TOKEN, BOT_NAME);", "java"));

    }
}
