import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceFile {
        public static String readFile(String filePath) throws IOException {
            if(!Files.exists(Paths.get(filePath))) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }
        public static void writeFile(String filePath, String content) throws FileWriteException  {
            try {
                Files.write(Paths.get(filePath), content.getBytes());
            } catch (IOException e) {
                throw new FileWriteException("Cannot write to file: " + filePath);
            }
        }
        public static class FileWriteException extends Exception{
            public FileWriteException(String message){
                super(message);
            }
        }

    }

