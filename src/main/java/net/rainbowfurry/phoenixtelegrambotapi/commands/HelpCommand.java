package net.rainbowfurry.phoenixtelegrambotapi.commands;

import net.rainbowfurry.phoenixtelegrambotapi.TextFormatter;
import net.rainbowfurry.phoenixtelegrambotapi.TextMessage;
import net.rainbowfurry.phoenixtelegrambotapi.TelegramBot;

import java.util.Map;

public class HelpCommand implements Command{
    @Override
    public String getCommandDescription() {
        return "Get a List with all valid Commands";
    }

    @Override
    public void command(String channelID, String message) {

        String _message = "A Help List with all valid Bot Commands:\n\n";

        for(Map.Entry<String, Command> entry : TelegramBot.commands.entrySet()){
            _message += "/" + TextFormatter.bold(entry.getKey()) + " - " + entry.getValue().getCommandDescription() + "\n";
        }

        TextMessage.sendMessage(channelID, _message);
    }
}
