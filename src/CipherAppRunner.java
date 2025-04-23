
public class CipherAppRunner {
    public void start(String[] args) {
        if (args.length < CommandConstants.MIN_ARGS) {
            System.out.println("Please use File format: java -jar myApp.jar <ENCRYPT|DECRYPT|BRUTE_FORCE> <filePath> [key]");
            return;
        }

        String command = args[CommandConstants.COMMAND_INDEX].toUpperCase();
        String filePath = args[CommandConstants.FILE_INDEX];
        int key = args.length > CommandConstants.KEY_INDEX ? Integer.parseInt(args[CommandConstants.KEY_INDEX]) : 0;

        new Dispatcher().run(command, filePath, key);
    }
}

