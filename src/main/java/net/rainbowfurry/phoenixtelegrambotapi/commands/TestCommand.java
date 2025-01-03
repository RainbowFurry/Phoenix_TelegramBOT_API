package net.rainbowfurry.phoenixtelegrambotapi.commands;

import net.rainbowfurry.phoenixtelegrambotapi.BotMessage;

public class TestCommand implements Command{

    @Override
    public void command(String channelID, String message) {
        BotMessage.sendMessage(channelID, "TEST TEST TEST");
    }
}
