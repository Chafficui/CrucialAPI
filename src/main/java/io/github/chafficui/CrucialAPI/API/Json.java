package io.github.chafficui.CrucialAPI.API;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class Json {
    static Gson g = new Gson();

    public static void saveFile(String json, String filename){
        try (FileWriter file = new FileWriter(filename)) {

            file.write(json);
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadFile(String filename){
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toJson(Object object){
        return g.toJson(object);
    }

    public static <T> T fromJson(String jsonFilePath, Class<T> objectType){
        return g.fromJson(loadFile(jsonFilePath), objectType);
    }

    public static <T> T fromJson(String jsonFilePath, Type type){
        return g.fromJson(loadFile(jsonFilePath), type);
    }
}
