package net.rainbowfurry.phoenixtelegrambotapi.commands;

import net.rainbowfurry.phoenixtelegrambotapi.PhoenixBot;

public class TestCommand implements Command{

    @Override
    public void command(String channelID, String message) {
        PhoenixBot.instance.sendMessage(channelID, "TEST TEST TEST");
    }
}
