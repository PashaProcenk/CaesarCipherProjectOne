public class BruteForceKey {
        public static String bruteForce(String text){
            for (int i = 1; i < CaesarCipher.ALPHABET.length(); i++) {
                String decrypted = CaesarCipher.decrypt(text,i);
                if(isSyllable(decrypted)){
                    return decrypted;

                }

            }
            return "Brute force loser!";
        }
        private static boolean isSyllable(String text){
            return text.contains("ch") || text.contains("he") || text.contains("rc");
        }
    }

