package com.isnakebuzz.worldmanager.Utils;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class JsonConfig {

    @SneakyThrows
    public static void writeFile(File file, String data) {
        FileWriter myWriter = new FileWriter(file);
        myWriter.write(data);
        myWriter.close();
    }

    @SneakyThrows
    public static String readFile(File file) {
        StringBuilder builder = new StringBuilder();

        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            builder.append(myReader.nextLine());
        }
        myReader.close();

        return builder.toString();
    }

}
