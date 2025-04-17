import java.io.IOException;

public class Dispatcher {
    public static void run(String command, String filePath, int key) {
        try {
            String content = ServiceFile.readFile(filePath);
            String result;
            String outputFilePath;

            if (CommandConstants.ENCRYPT.equals(command)) {
                result = CaesarCipher.encrypt(content, key);
                outputFilePath = filePath.replace(".txt", CommandConstants.ENCRYPTED_SUFFIX);
            } else if (CommandConstants.DECRYPT.equals(command)) {
                result = CaesarCipher.decrypt(content, key);
                outputFilePath = filePath.replace(CommandConstants.ENCRYPTED_SUFFIX, CommandConstants.DECRYPTED_SUFFIX);
            } else if (CommandConstants.BRUTE_FORCE.equals(command)) {
                result = new BruteForceKey().bruteForce(content);
                outputFilePath = filePath.replace(CommandConstants.ENCRYPTED_SUFFIX, CommandConstants.BRUTE_FORCED_SUFFIX);
            } else {
                System.out.println("Invalid command. Use ENCRYPT, DECRYPT, or BRUTE_FORCE.");
                return;
            }
            ServiceFile.writeFile(outputFilePath, result);
            System.out.println("Operation completed. Output file: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        } catch (ServiceFile.FileWriteException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}
