public class CaesarCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,«»\"'!:? -;(){}[]@#%^&*/\\<>~`=_+|";

    public static String encrypt(String text, int key) {
        return shiftText(text, key);
    }

    public static String decrypt(String text, int key) {
        return shiftText(text, -key);
    }

    private static String shiftText(String text , int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index != -1) {
                int newIndex = (index + shift + ALPHABET.length()) & ALPHABET.length();
                result.append(ALPHABET.charAt(newIndex));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}

