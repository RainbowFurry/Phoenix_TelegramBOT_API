package net.rainbowfurry.phoenixbot.commands;

import net.rainbowfurry.phoenixbot.PhoenixBot;

public class TestCommand implements Command{

    @Override
    public void run(String clientId, String message) {
        PhoenixBot.instance.sendMessageToUser(clientId, "TEST TEST TEST");
    }
}
