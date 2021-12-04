package org.example.javacodesnippets.file;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class WriteDataToFile {
    private final String fileNameToWrite = "/tmp/WriteDataToFile";
    private final List<String> fileContents = Arrays.asList("This is line number one", "This is line number 2", "Line 3 Added");
    private final String fileContentsToString = String.join(",", fileContents);

    public static void main(String[] args) throws IOException {
        WriteDataToFile writeDataToFile = new WriteDataToFile();

        // Example - 1.1 : Write to file using java.nio.file.Files class with write method
        writeDataToFile.usingWriteFromFilesClass();

        // Example - 1.2 : Copy data to file using java.nio.file.Files class with Copy method
        writeDataToFile.usingCopyFromFilesClass();

        // Files.write has Overloaded methods available for changing the Charset while writing.
        // Files.copy cannot write to file by default, we have to inform copy method whether to replace the exisiting or not.

        // Example - 2.1 : Write to file using FileWriter Class with write method
        writeDataToFile.usingWriteFromFileWriterClass();

        // Example - 2.2 : Write to file using BufferedWriter Class with write method
        writeDataToFile.usingWriteFromBufferedWriterClass();

        // FileWriter() vs BufferedWriter()
        // Writing to files is costly operation,
        // BufferedWriter is preferred if there are multiple write in the logic.
        // FileWriter is suitable for one time write.
    }

    /*
        Example - 1.1 : Write to file using java.nio.file.Files class with write method
     */
    public void usingWriteFromFilesClass() throws IOException {
        // Paths.get() : Converts String to Path object that can locate a file in the file system.
        // Files.write() : Write lines of text to a file. By default, uses UTF-8 charset.
        // String.join() : Can join the String type iterable with the specified delimiter.

        Path pathForFileNameToWrite = Paths.get(fileNameToWrite + "1.txt");
        Files.write(pathForFileNameToWrite, fileContents);

        compareStringContent(
                String.join(",", Files.readAllLines(pathForFileNameToWrite)),
                fileContentsToString
        );
    }

    /*
        Example - 1.2 : Copy data to file using java.nio.file.Files class with Copy method
     */
    public void usingCopyFromFilesClass() throws IOException {
        // Paths.get() : Converts String to Path object that can locate a file in the file system.
        // Files.copy : Copies the InputStream data to a file based on the CopyOptions.
        // IOUtils.toInputStream : CommonsIO Library, helps to convert String to InputStream. Based on the Input Charset can also be changed.
        // String.join() : Can join the String type iterable with the specified delimiter.

        Path pathForFileNameToWrite = Paths.get(fileNameToWrite + "2.txt");

        Files.copy(
                IOUtils.toInputStream(String.join("\n", fileContents), Charset.defaultCharset()),
                pathForFileNameToWrite,
                StandardCopyOption.REPLACE_EXISTING
        );

        compareStringContent(
                String.join(",", Files.readAllLines(pathForFileNameToWrite)),
                fileContentsToString
        );
    }

    /*
        Example - 2.1 : Write to file using FileWriter Class with write method
     */
    public void usingWriteFromFileWriterClass() throws IOException {
        FileWriter fileWriter = new FileWriter(new File(fileNameToWrite + "3.txt"));
        fileWriter.write(String.join(",", fileContents));
        fileWriter.close();

        compareStringContent(
                String.join(",", Files.readAllLines(Paths.get(fileNameToWrite + "3.txt"))),
                fileContentsToString
        );
    }

    /*
        Example - 2.2 : Write to file using BufferedWriter Class with write method
     */
    public void usingWriteFromBufferedWriterClass() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameToWrite + "4.txt"));
        for(String line : fileContents ) {
            bufferedWriter.write(line);
        }
        bufferedWriter.flush();
        bufferedWriter.close();

        compareStringContent(
                String.join(",", Files.readAllLines(Paths.get(fileNameToWrite + "4.txt"))),
                fileContentsToString.replace(",","")
        );
    }

    public void compareStringContent(String fileNameV1Content, String fileNameV2Content) {
        System.out.println((fileNameV1Content.equals(fileNameV2Content))?" File content has to be same!":"Something went wrong!");
        System.out.println("fileNameV1Content : " + fileNameV1Content);
        System.out.println("fileNameV2Content : " + fileNameV2Content);
    }
}
