package compiler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileUtils {

    public static String readFile (File file) {
        String text = "";
        try {
            text = Files.readString(file.toPath(), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void writeToFile (File file, String content) {
        try {
            Files.writeString(file.toPath(), content, StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
