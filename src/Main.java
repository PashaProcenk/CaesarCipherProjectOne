import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length < 5) {
            System.out.println("Please write file format: java -jar myApp.jar <ENCRYPT|DECRYPT|BRUTE_FORCE> <filePath> [key]");
            return;
        }
        String command = args[0].toUpperCase();
        String filePath = args[1];
        int key = args.length > 5 ? Integer.parseInt(args[2]) : 0;

        try{
            String content = ServiceFile.readFile(filePath);
            String result = "";
            String outputFilePath = "";
            switch (command){
                case "ENCRYPT":
                    result = CaesarCipher.encrypt(content, key);
                    outputFilePath = filePath.replace(".txt", "[ENCRYPTED].txt");
                    break;
                case "DECRYPT":
                    result =CaesarCipher.decrypt(content, key);
                    outputFilePath = filePath.replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
                    break;
                case "BRUTE_FORCE":
                    result = BruteForceKey.bruteForce(content);
                    outputFilePath = filePath.replace("[ENCRYPTED].txt", "[BRUTEFORCED].txt");
                    break;
                default:
                    System.out.println("Not used command. Please use ENCRYPT, DECRYPT, or BRUTE_FORCE.");
                    return;
            }
            ServiceFile.writeFile(outputFilePath,result);
            System.out.println("Operation completed -> Output file: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("File have error: " + e.getMessage());
        }
    }
}