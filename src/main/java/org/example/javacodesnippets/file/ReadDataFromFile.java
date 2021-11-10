package org.example.javacodesnippets.file;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadDataFromFile {
    // Version-1 to get the file Path : Using getResource() method
    private final URL fileNameV1 = this.getClass().getClassLoader().getResource("ReadDataFromFile_TextFile.txt");
    // Version-2 to get the file Path : Using System.getProperty("user.dir") method
    private final String fileNameV2 = System.getProperty("user.dir") + "/src/main/resources/ReadDataFromFile_TextFile.txt";

    public static void main(String[] args) throws IOException {
        ReadDataFromFile readDataFromFile = new ReadDataFromFile();

        // Example - 1 : Easy way to read files is through java.nio.file.Files class
        readDataFromFile.readAllLinesFromFilesClass();

    }

    /*
        Example - 1 : Easy way to read files is through java.nio.file.Files class
     */
    public void readAllLinesFromFilesClass() throws IOException {
        // Files.readAllLines() : returns list of Strings took from file. Each entry in the List is a line from the file.
        // String.join() : Can join the String type iterable with the specified delimiter.
        String fileNameV1Content = String.join("", Files.readAllLines(Paths.get(this.fileNameV1.getFile()), StandardCharsets.UTF_8));
        String fileNameV2Content = String.join("", Files.readAllLines(Paths.get(this.fileNameV2), StandardCharsets.UTF_8));

        if(fileNameV1Content.equals(fileNameV2Content)) {
            System.out.println(" File content has to be same!");

        } else {
            System.out.println("Something went wrong!");
        }

        System.out.println("fileNameV1Content : " + fileNameV1Content);
        System.out.println("fileNameV2Content : " + fileNameV2Content);
    }
}
