package io.github.chafficui.CrucialAPI.io;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class Json {
    static Gson g = new Gson();

    public static void saveFile(String json, String filename) throws IOException {
        FileWriter file = new FileWriter(filename);
        file.write(json);
        file.flush();
    }

    public static String loadFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String everything = sb.toString();
        br.close();
        return everything;
    }

    public static String toJson(Object object){
        return g.toJson(object);
    }

    public static <T> T fromJson(String jsonFilePath, Class<T> objectType) throws IOException {
        return g.fromJson(loadFile(jsonFilePath), objectType);
    }

    public static <T> T fromJson(String jsonFilePath, Type type) throws IOException {
        return g.fromJson(loadFile(jsonFilePath), type);
    }
}