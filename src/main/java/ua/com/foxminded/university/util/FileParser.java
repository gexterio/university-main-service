package ua.com.foxminded.university.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileParser {

   private final FileReader reader;

    @Autowired
    public FileParser(FileReader reader) {
        this.reader = reader;
    }

    public List<String> parse(String fileName) {
        return reader.readFile(fileName);
    }

}
