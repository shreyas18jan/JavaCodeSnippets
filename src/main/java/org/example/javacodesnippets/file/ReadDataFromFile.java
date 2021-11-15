package org.example.javacodesnippets.file;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class ReadDataFromFile {
    // Version-1 to get the file Path : Using getResource() method
    private final URL fileNameV1 = this.getClass().getClassLoader().getResource("ReadDataFromFile_TextFile.txt");
    // Version-2 to get the file Path : Using System.getProperty("user.dir") method
    private final String fileNameV2 = System.getProperty("user.dir") + "/src/main/resources/ReadDataFromFile_TextFile.txt";

    public static void main(String[] args) throws IOException {
        ReadDataFromFile readDataFromFile = new ReadDataFromFile();

        // Example - 1.1 : Read file data using java.nio.file.Files class with readAllLines method
        readDataFromFile.usingReadAllLinesFromFilesClass();

        // Example - 1.2 : Read file data using java.nio.file.Files class with lines method
        readDataFromFile.usingLinesMethodFromFilesClass();

        // Compared to Files.readAllLines() vs Files.lines() :
        // readAllLines() - is preferred when the file is small.
        // lines() - is preferred when the file is large.

    }

    /*
        Example - 1.1 : Read file data using java.nio.file.Files class with readAllLines method
     */
    public void usingReadAllLinesFromFilesClass() throws IOException {
        // Paths.get() : Converts String to Path object that can locate a file in the file system.
        // Files.readAllLines() : returns list of Strings took from file. Each entry in the List is a line from the file.
        // String.join() : Can join the String type iterable with the specified delimiter.
        String fileNameV1Content = String.join("", Files.readAllLines(Paths.get(this.fileNameV1.getFile()), StandardCharsets.UTF_8));
        String fileNameV2Content = String.join("", Files.readAllLines(Paths.get(this.fileNameV2), StandardCharsets.UTF_8));

        compareStringContent(fileNameV1Content, fileNameV2Content);
    }

    /*
        Example - 1.2 : Read file data using java.nio.file.Files class with lines method
     */
    public void usingLinesMethodFromFilesClass() throws IOException {
        // Paths.get() : Converts String to an object that can locate a file in the file system.
        // Files.lines() : returns Stream of Strings took from file. Each entry in the entry is a line from the file.
        // Collectors.joining() : Takes Stream of String and joins it based on the delimiter, by default join as it is.
        String fileNameV1Content = Files.lines(Paths.get(this.fileNameV1.getFile()), StandardCharsets.UTF_8).collect(Collectors.joining());
        String fileNameV2Content = Files.lines(Paths.get(this.fileNameV2), StandardCharsets.UTF_8).collect(Collectors.joining());

        compareStringContent(fileNameV1Content, fileNameV2Content);
    }

    public void compareStringContent(String fileNameV1Content, String fileNameV2Content) {
        System.out.println((fileNameV1Content.equals(fileNameV2Content))?" File content has to be same!":"Something went wrong!");
        System.out.println("fileNameV1Content : " + fileNameV1Content);
        System.out.println("fileNameV2Content : " + fileNameV2Content);
    }
}
