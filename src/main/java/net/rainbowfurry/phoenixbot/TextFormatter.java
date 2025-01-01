package net.rainbowfurry.phoenixbot;

public class TextFormatter {

    public static String bold(String text){
        return String.format("**%s**", text);
    }

    public static String italic(String text){
        return String.format("*%s*", text);
    }

    public static String underline(String text){
        return String.format("__%s__", text);
    }

    public static String strikeThrough(String text){
        return String.format("~~%s~~", text);
    }

    public static String spoiler(String text){
        return String.format("||%s||", text);
    } //?

}
