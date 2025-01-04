package net.rainbowfurry.phoenixtelegrambotapi;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonMessage {

    private InlineKeyboardMarkup markupInline;
    private List<List<InlineKeyboardButton>> mainRow;
    private List<InlineKeyboardButton> row;
    private InlineKeyboardButton button;

    public ButtonMessage() {

        // Create inline keyboard
        markupInline = new InlineKeyboardMarkup();
        mainRow = new ArrayList<>();

    }

    public void createRow(){
        row = new ArrayList<>();
    }

    public void addButton(InlineKeyboardButton button){
        row.add(button);
    }

    public void completeRow(){
        mainRow.add(row);
    }

    public void completeRow(List<InlineKeyboardButton> row){
        mainRow.add(row);
    }

    public InlineKeyboardButton createButton(String displayText, String option){
        button = new InlineKeyboardButton();
        button.setText(displayText);
        button.setCallbackData(option);
        return button;
    }

    //message.setReplyMarkup(markupInline);

    /*
    *   String callbackData = update.getCallbackQuery().getData();
    *   if ("option1".equals(callbackData)) {
            response.setText("You selected Option 1!");
        } else if ("option2".equals(callbackData)) {
            response.setText("You selected Option 2!");
        }
    * */

    public InlineKeyboardMarkup build(){
        // Set the keyboard to the markup
        markupInline.setKeyboard(mainRow);
        return markupInline;
    }

}
