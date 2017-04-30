package com.first.manoj;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.first.manoj.KeyPhrase;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kanhu on 30/4/17.
 */

public class ToJSON {

    public static void main(String[] args) {

        KeyPhrase keyPhrase = new KeyPhrase();
        keyPhrase.setKeyName("qwe");
        keyPhrase.setPassPhrase("twd");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String keyJson = gson.toJson(keyPhrase);

        System.out.println(keyJson);

        try {
            FileWriter fileWriter = new FileWriter("key.json");
            fileWriter.write(keyJson);
            fileWriter.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
