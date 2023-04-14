package com.PBL3.config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
   public static Dotenv load(){
        return Dotenv.configure().directory("D:\\PBL3.1\\19-1-2023\\assets").filename("env").load();
    }
}
