import java.io.IOException;

public class CipherAppRunner {
    public void start(String[] args) {
        if (args.length < CommandConstants.MIN_ARGS) {
            System.out.println("Please use File format: java -jar myApp.jar <ENCRYPT|DECRYPT|BRUTE_FORCE> <filePath> [key]");
            return;
        }

        String command = args[CommandConstants.COMMAND_INDEX].toUpperCase();
        String filePath = args[CommandConstants.FILE_INDEX];
        int key = args.length > CommandConstants.KEY_INDEX ? Integer.parseInt(args[CommandConstants.KEY_INDEX]) : 0;

        dispatcher(command, filePath, key);
    }

    public void dispatcher(String command, String filePath, int key) {
        try {
            String content = ServiceFile.readFile(filePath);
            String result;
            String outputFilePath;

            switch (command) {
                case CommandConstants.ENCRYPT:
                    result = CaesarCipher.encrypt(content, key);
                    outputFilePath = filePath.replace(".txt", CommandConstants.ENCRYPTED_SUFFIX);
                    break;
                case CommandConstants.DECRYPT:
                    result = CaesarCipher.decrypt(content, key);
                    outputFilePath = filePath.replace(CommandConstants.ENCRYPTED_SUFFIX, CommandConstants.DECRYPTED_SUFFIX);
                    break;
                case CommandConstants.BRUTE_FORCE:
                    result = BruteForceKey.bruteForce(content);
                    outputFilePath = filePath.replace(CommandConstants.ENCRYPTED_SUFFIX, CommandConstants.BRUTE_FORCED_SUFFIX);
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
