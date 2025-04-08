import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServiceFile {
        public static String readFile(String filePath) throws IOException {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }
        public static void writeFile(String filePath, String content)  {
            try {
                Files.write(Paths.get(filePath), content.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

