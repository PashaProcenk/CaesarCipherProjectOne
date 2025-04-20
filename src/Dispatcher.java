import java.io.IOException;

public class Dispatcher {
     public void run(String command, String filePath, int key) {
         try {
             CommandType commandType = CommandType.fromString(command);
             if (commandType == null) {
                 System.out.println("Invalid command. Use ENCRYPT, DECRYPT, or BRUTE_FORCE.");
                 return;
             }

             String content = ServiceFile.readFile(filePath);
             String result = commandType.process(content, key);
             String outputFilePath = commandType.getOutputFileName(filePath);
             ServiceFile.writeFile(outputFilePath, result);
             System.out.println("Operation completed. Output file: " + outputFilePath);
         } catch (IOException e) {
             System.out.println("File error: " + e.getMessage());
         } catch (ServiceFile.FileWriteException e) {
             System.out.println("Write error: " + e.getMessage());
         }
     }
 }

 enum CommandType {
     ENCRYPT(CommandConstants.ENCRYPTED_SUFFIX) {
         public String process(String content, int key) {
             return CaesarCipher.encrypt(content, key);
         }

         public String getOutputFileName(String inputFileName) {
             return inputFileName.replace(".txt", suffix);
         }
     },
     DECRYPT(CommandConstants.DECRYPTED_SUFFIX) {
         public String process(String content, int key) {
             return CaesarCipher.decrypt(content, key);
         }

         public String getOutputFileName(String inputFileName) {
             return inputFileName.replace(CommandConstants.ENCRYPTED_SUFFIX, suffix);
         }
     },
     BRUTE_FORCE(CommandConstants.BRUTE_FORCED_SUFFIX) {
         public String process(String content, int key) {
             return new BruteForceKey().bruteForce(content);
         }

         public String getOutputFileName(String inputFileName) {
             return inputFileName.replace(CommandConstants.ENCRYPTED_SUFFIX, suffix);
         }
     };

     protected final String suffix;

     CommandType(String suffix) {
         this.suffix = suffix;
     }

     public abstract String process(String content, int key);
     public abstract String getOutputFileName(String inputFileName);

     public static CommandType fromString(String command) {
         for (CommandType type : values()) {
             if (type.name().equalsIgnoreCase(command)) {
                 return type;
             }
         }
         return null;
     }
}
