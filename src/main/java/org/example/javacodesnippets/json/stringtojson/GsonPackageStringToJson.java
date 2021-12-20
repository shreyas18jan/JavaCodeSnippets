package org.example.javacodesnippets.json.stringtojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.example.javacodesnippets.json.model.AvailableLaptops;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class GsonPackageStringToJson {
    public static void main(String[] args) throws Exception {
        String stringInputFileName = System.getProperty("user.dir") + "/src/main/resources/StringInputForJson.txt";
        String stringInputFileContent = Files.lines(Paths.get(stringInputFileName)).collect(Collectors.joining());

        GsonPackageStringToJson gsonPackageStringToJson = new GsonPackageStringToJson();

        // Example - 1 : Convert String to Json using Gson class with fromJson method
        gsonPackageStringToJson.usingFromJsonFromGsonPackage(stringInputFileContent);

        // Example - 2 : Convert String to Json using JsonParser class with parseString method
        gsonPackageStringToJson.usingParseFromGsonPackage(stringInputFileContent);

        // 1. If we want to add restricts during Serialization or DeSerialization is possible by using GsonBuilder()
        // 2. If the String starts with JsonArray, we need to tackle it using Type keyword
        // Example:-
        //          Type AvailableLaptopListType = new TypeToken<ArrayList<AvailableLaptops>>(){}.getType();
        //          ArrayList<AvailableLaptops> availableLaptopList = gson.fromJson(stringContent, AvailableLaptopListType);

    }

    /*
        Example - 1 : Convert String to Json using Gson class with fromJson method
     */
    public void usingFromJsonFromGsonPackage(String stringContent) {
        Gson gson = new Gson();
        AvailableLaptops availableLaptops = gson.fromJson(stringContent, AvailableLaptops.class);
        System.out.println("usingFromJsonFromGsonPackage()");
        System.out.println(availableLaptops);
        System.out.println();
    }

    /*
        Example - 2 : Convert String to Json using JsonParser class with parseString method
     */
    public void usingParseFromGsonPackage(String stringContent) {
        JsonElement jsonElement = JsonParser.parseString(stringContent);
        // Converts the String to generic JsonElement.
        System.out.println("usingParseFromGsonPackage()");
        System.out.println(jsonElement);
        System.out.println();
    }
}
