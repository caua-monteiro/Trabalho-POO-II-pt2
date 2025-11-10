package com.appacademia.util;

import java.io.InputStream;
import java.util.Properties;

public class LeitorPDF {
    private static final Properties properties = new Properties();

    static {
        try(InputStream input = LeitorPDF.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(input == null){
                System.out.println("Arquivo config.properties nao foi encontrado");
            }
            properties.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String Key){
        return properties.getProperty(Key);
    }
}
