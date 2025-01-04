package net.rainbowfurry.phoenixtelegrambotapi.commands;

import net.rainbowfurry.phoenixtelegrambotapi.ButtonMessage;
import net.rainbowfurry.phoenixtelegrambotapi.TextMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TestCommand implements Command{

    @Override
    public String getCommandDescription() {
        return "Test";
    }

    @Override
    public void command(String channelID, String message) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(channelID);
        sendMessage.setText("TEST");

        ButtonMessage buttonMessage = new ButtonMessage();
        buttonMessage.createRow();
        buttonMessage.addButton(buttonMessage.createButton("Option 1", "option1"));
        buttonMessage.addButton(buttonMessage.createButton("Option 2", "option2"));
        buttonMessage.completeRow();
        buttonMessage.createRow();
        buttonMessage.addButton(buttonMessage.createButton("Test", "test"));
        buttonMessage.completeRow();
        buttonMessage.createRow();
        buttonMessage.addButton(buttonMessage.createButton("Test 1", "test1"));
        buttonMessage.addButton(buttonMessage.createButton("Test 2", "test2"));
        buttonMessage.completeRow();

        sendMessage.setReplyMarkup(buttonMessage.build());

        TextMessage.sendMessage(sendMessage);
        TextMessage.sendMessage(channelID, "TEST TEST TEST");
    }
}
