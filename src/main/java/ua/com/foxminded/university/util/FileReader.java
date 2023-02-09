package ua.com.foxminded.university.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Component
public class FileReader {

    public List<String> readFile(String fileName) {
        if (fileName == null || fileName.isEmpty() || fileName.isBlank()) {
            return Collections.emptyList();
        }
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (resource == null) {
                return Collections.emptyList();
            }
            ArrayList<String> list = new ArrayList<>();
            try (Scanner scanner = new Scanner(resource)) {
                while (scanner.hasNext()) {
                    list.add(scanner.nextLine());
                }
                return list;
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
