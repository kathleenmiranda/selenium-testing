package app.vercel.northwind.utils;

import java.util.UUID;

public class DataGenerator {

    public static String gerarEmail() {
        return "qa_" + UUID.randomUUID() + "@teste.com";
    }

}