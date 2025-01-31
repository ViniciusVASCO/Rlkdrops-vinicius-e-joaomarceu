package vinicius.joaomarceu.utils;

public class Env {

    private static Dotenv dotenv;
    private Env(){}
    public static String get(String key){
        if(dotenv == null){
            dotenv = Dotenv.load();
        }
        return dotenv.get(key);
    }
}