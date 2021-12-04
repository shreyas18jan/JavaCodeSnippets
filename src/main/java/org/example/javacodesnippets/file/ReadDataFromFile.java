package org.example.javacodesnippets.file;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
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

        // Example - 1.3 : Read file data using java.nio.file.Files class with readAllBytes method
        readDataFromFile.usingReadAllBytesMethodFromFilesClass();

        // Files.readAllLines() vs Files.lines() vs Files.readAllBytes() :
        // readAllLines() - is preferred when the file is small.
        // lines() - is preferred when the file is large.
        // readAllBytes() - is preferred when the file type is Binary Data or Image Data.

        // Example - 2.1 : Read file data using BufferedReader class with readLine method
        readDataFromFile.usingReadLineMethodFromBufferedReaderClass();

        // Example - 2.2 : Read file data using Scanner class with nextLine method
        readDataFromFile.usingNextLineMethodFromScannerClass();

        // Scanner vs BufferedReader
        // Scanner is preferred, if stream of characters needs to be parsed in different ways.
        // BufferedReader is preferred, if we need to read the data as it is.

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

    /*
        Example - 1.3 : Read file data using java.nio.file.Files class with readAllBytes method
     */
    public void usingReadAllBytesMethodFromFilesClass() throws IOException {
        // Paths.get() : Converts String to an object that can locate a file in the file system.
        // Files.readAllBytes() : Reads all the bytes from a file. Similar to Files.readAllLines(), but not bound to String type.
        String fileNameV1Content = new String(Files.readAllBytes(Paths.get(this.fileNameV1.getFile())));
        String fileNameV2Content = new String(Files.readAllBytes(Paths.get(this.fileNameV2)));

        compareStringContent(fileNameV1Content, fileNameV2Content);
    }

    /*
        Example - 2.1 : Read file data using BufferedReader class with readLine method
     */
    public void usingReadLineMethodFromBufferedReaderClass() throws IOException {
        // Paths.get() : Converts String to an object that can locate a file in the file system.
        // FileReader : Extends InputStream which is used for reading character files.
        // BufferedReader : Reads text from a Reader/InputStream, buffers characters for efficient reading of characters, arrays, and lines.
        FileReader fileOneReader = new FileReader(this.fileNameV1.getFile());
        BufferedReader fileNameV1ContentReader = new BufferedReader(fileOneReader);

        FileReader fileTwoReader = new FileReader(this.fileNameV2);
        BufferedReader fileNameV2ContentReader = new BufferedReader(fileTwoReader);

        StringBuilder fileNameV1Content = new StringBuilder();
        String line;
        while((line=fileNameV1ContentReader.readLine()) != null) {
            fileNameV1Content.append(line);
        }

        StringBuilder fileNameV2Content = new StringBuilder();
        while((line=fileNameV2ContentReader.readLine()) != null) {
            fileNameV2Content.append(line);
        }

        compareStringContent(fileNameV1Content.toString(), fileNameV2Content.toString());
    }

    /*
        Example - 2.2 : Read file data using Scanner class with nextLine method
     */
    private void usingNextLineMethodFromScannerClass() throws FileNotFoundException {
        // Scanner : A simple text scanner which can parse primitive types and strings using regular expressions.
        Scanner fileNameV1Scanner = new Scanner(new File(this.fileNameV1.getFile()));
        StringBuilder fileNameV1Content = new StringBuilder();
        Scanner fileNameV2Scanner = new Scanner(new File(this.fileNameV2));
        StringBuilder fileNameV2Content = new StringBuilder();

        while(fileNameV1Scanner.hasNextLine()) {
            fileNameV1Content.append(fileNameV1Scanner.nextLine());
        }

        while(fileNameV2Scanner.hasNextLine()) {
            fileNameV2Content.append(fileNameV2Scanner.nextLine());
        }

        compareStringContent(fileNameV1Content.toString(), fileNameV2Content.toString());
    }

    public void compareStringContent(String fileNameV1Content, String fileNameV2Content) {
        System.out.println((fileNameV1Content.equals(fileNameV2Content))?" File content has to be same!":"Something went wrong!");
        System.out.println("fileNameV1Content : " + fileNameV1Content);
        System.out.println("fileNameV2Content : " + fileNameV2Content);
    }
}
