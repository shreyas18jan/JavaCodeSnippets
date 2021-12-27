package org.example.javacodesnippets.json.stringtojson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.example.javacodesnippets.json.model.AvailableLaptops;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class JacksonPackageStringToJson {
    public static void main(String[] args) throws Exception {
        final String stringInputForJsonFile = System.getProperty("user.dir") + "/src/main/resources/StringInputForJson.txt";
        final String stringInputForJsonFileContent = Files.lines(Paths.get(stringInputForJsonFile))
                .collect(Collectors.joining("\n"));

        JacksonPackageStringToJson stringToJson = new JacksonPackageStringToJson();

        // Example - 1 : Convert String to Json using jackson.databind.ObjectMapper class with readValue method
        stringToJson.usingReadValueFromJacksonDataBindPackage(stringInputForJsonFileContent);

        // Example - 2 : Convert String to Json using jackson.databind.ObjectMapper class with treeToValue method
        stringToJson.usingTreeToValueFromJacksonDataBindPackage(stringInputForJsonFile);

        // Example - 3 : Convert String to Json using jackson.core.JsonParser class with readValueAs method
        stringToJson.usingParserFromJacksonCorePackage(stringInputForJsonFile);

        // objectMapper.readValue() - Preferred when we know the Object Type. Used when playing with with POJOs.
        // objectMapper.treeToValue() - Preferred when we don't know the Object Type
        // jsonFactory.createParser() - Preferred when we are dealing with reads and writes JSON content as discrete events
    }

    /*
        Example - 1 : Convert String to Json using jackson.databind.ObjectMapper class with readValue method
     */
    public void usingReadValueFromJacksonDataBindPackage(String jsonContent) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        AvailableLaptops availableLaptops = objectMapper.readValue(jsonContent, AvailableLaptops.class);
        System.out.println("usingReadValueFromJacksonDataBindPackage()");
        System.out.println(availableLaptops);
        System.out.println();
    }

    /*
        Example - 2 : Convert String to Json using jackson.databind.ObjectMapper class with treeToValue method
     */
    public void usingTreeToValueFromJacksonDataBindPackage(String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // We can ignore the error if we encounter unknown property/ies
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // Method to deserialize JSON content as tree expressed using set of JsonNode instances.
        JsonNode jsonNode = objectMapper.readTree(new File(fileName));
        AvailableLaptops availableLaptops = objectMapper.treeToValue(jsonNode, AvailableLaptops.class);
        System.out.println("usingTreeToValueFromJacksonDataBindPackage()");
        System.out.println(availableLaptops);
        System.out.println();
    }

    /*
        Example - 3 : Convert String to Json using jackson.core.JsonParser class with readValueAs method
     */
    public void usingParserFromJacksonCorePackage(String fileName) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.setCodec(new ObjectMapper());
        JsonParser jsonParser = jsonFactory.createParser(new File(fileName));
        // We can ignore the error if we encounter unknown property/ies
        jsonParser.disable(JsonParser.Feature.IGNORE_UNDEFINED);
        AvailableLaptops availableLaptops = jsonParser.readValueAs(AvailableLaptops.class);
        System.out.println("usingParserFromJacksonCorePackage()");
        System.out.println(availableLaptops);
        System.out.println();
    }
}
