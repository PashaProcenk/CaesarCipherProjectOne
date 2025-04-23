public class BruteForceKey {
    public String bruteForce(String text) {
        for (int i = 1; i < CaesarCipher.ALPHABET.length(); i++) {
            String decrypted = CaesarCipher.shiftText(text, -i);
            if (isSyllable(decrypted)) {
                return decrypted;
                }

            }
            return "Brute force loser!";
        }
        private static boolean isSyllable(String text){
            return text.contains("ch") || text.contains("he") || text.contains("rc");
        }
    }

