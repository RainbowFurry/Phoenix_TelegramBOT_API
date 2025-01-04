package net.rainbowfurry.phoenixtelegrambotapi;

public class TextFormatter {

    public static String bold(String text){
        return String.format("<b>%s</b>", text);
    }

    public static String boldMarkup(String text){
        return String.format("**%s**", text);
    }

    public static String italic(String text){
        return String.format("<i>%s</i>", text);
    }

    public static String italicMarkup(String text){
        return String.format("*%s*", text);
    }

    public static String underline(String text){
        return String.format("<u>%s</u>", text);
    }

    public static String underlineMarkup(String text){
        return String.format("__%s__", text);
    }

    public static String strikeThrough(String text){
        return String.format("<s>%s</s>", text);
    }

    public static String strikeThroughMarkup(String text){
        return String.format("~~%s~~", text);
    }

    public static String code(String text){
        return String.format("<code>%s</code>", text);
    }

    public static String codeMarkup(String text){
        return String.format("```%s```", text);
    }

    public static String codePre(String text, String language){
        return String.format("<pre language='%s'>%s</pre>", language, text);
    }

    public static String emoji(String text, String emojiID){
        return String.format("<tg-emoji emoji-id='%s'>%s</tg-emoji>", emojiID, text);
    }

    public static String color(String text){
        return String.format("<span style=\"color:blue\">%s</span>", text);
    }

    public static String spoiler(String text){
        return String.format("<tg-spoiler>%s</tg-spoiler>", text);
    }

    public static String block(String text){
        return String.format("<blockquote>%s</blockquote>", text);
    }

    public static String blockExpanded(String text){
        return String.format("<blockquote expandable>%s</blockquote>", text);
    }

    public static String spoilerMarkup(String text){
        return String.format("||%s||", text);
    }

}
