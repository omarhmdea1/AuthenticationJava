package authentication.server.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReadWriteToJson {

    public static void writeToJson(String fileName, Map<String,String> userMap){
        try(FileWriter fileWriter = new FileWriter(fileName)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userMap,fileWriter);
        } catch (IOException e) {
            throw new RuntimeException("Error opening file.",e);
        }
    }

    public static Map<String,String> readFromJson(String fileName){
        try(FileReader fileReader = new FileReader(fileName)){
            return new Gson().fromJson(fileReader,Map.class);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                throw new RuntimeException("file " + fileName + " doesn't exist." , e);
            } else {
                throw new RuntimeException("Error opening file.", e);
            }
        }
    }
}
