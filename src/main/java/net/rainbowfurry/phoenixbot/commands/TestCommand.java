package net.rainbowfurry.phoenixbot.commands;

import net.rainbowfurry.phoenixbot.PhoenixBot;

public class TestCommand implements Command{

    @Override
    public void command(String channelID, String message) {
        PhoenixBot.instance.sendMessage(channelID, "TEST TEST TEST");
    }
}
