import java.io.IOException;

public class CipherAppRunner {
    public void start(String[] args) {
        if (args.length < 5) {
            System.out.println("Please use file format: java -jar myApp.jar <ENCRYPT|DECRYPT|BRUTE_FORCE> <filePath> [key]");
            return;
        }
        String command = args[0].toUpperCase();
        String filePath = args[1];
        int key = args.length > 5 ? Integer.parseInt(args[2]) : 0;

        dispatcher(command, filePath, key);
    }

    public void dispatcher(String command, String filePath, int key) {
        try {
            String content = ServiceFile.readFile(filePath);
            String result;
            String outputFilePath;

            switch (command) {
                case "ENCRYPT":
                    result = CaesarCipher.encrypt(content, key);
                    outputFilePath = filePath.replace(".txt", "[ENCRYPTED].txt");
                    break;
                case "DECRYPT":
                    result = CaesarCipher.decrypt(content, key);
                    outputFilePath = filePath.replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
                    break;
                case "BRUTE_FORCE":
                    result = BruteForceKey.bruteForce(content);
                    outputFilePath = filePath.replace("[ENCRYPTED].txt", "[BRUTEFORCED].txt");
                    break;
                default:
                    System.out.println("Invalid command. Use ENCRYPT, DECRYPT, or BRUTE_FORCE.");
                    return;
            }
            ServiceFile.writeFile(outputFilePath, result);
            System.out.println("Operation completed. Output file -> " + outputFilePath);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (ServiceFile.FileWriteException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
