package net.rainbowfurry.phoenixtelegrambotapi.commands;

public interface Command {

    String getCommandDescription();

    void command(String channelID, String message);

}
