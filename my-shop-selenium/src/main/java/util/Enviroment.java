package util;

import io.github.cdimascio.dotenv.Dotenv;

public class Enviroment {

    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getMyshopDefaultEmail() {
        return dotenv.get("MYSHOP_DEFAUL_EMAIL");
    }

    public static String getMyshopDefaultPassword() {
        return dotenv.get("MYSHOP_DEFAUL_PASSWORD");
    }
}
