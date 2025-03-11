package src;

import Utils.BaseConvertor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    // ANSI color codes for better formatting
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BOLD = "\u001B[1m";

    public static void main(String args[]) {
        // Initialize required classes
        BaseConvertor converter = new BaseConvertor();
        Random rand = new Random();
        RSAKeyGenerator keyGen = new RSAKeyGenerator();
        RSAEncryptor encryptor = new RSAEncryptor();
        RSADecryptor decryptor = new RSADecryptor();

        // Generate RSA Keys
        BigInteger n = keyGen.n;
        BigInteger e = keyGen.Pub_key();
        BigInteger d = keyGen.Priv_key();

        // Input Message
        String message = "Hello world my name is awais.";

        // Stylish output
        System.out.println("\n" + BOLD + BLUE + "╔════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + BLUE + "║   🔐 RSA Encryption & Decryption   ║" + RESET);
        System.out.println(BOLD + BLUE + "╚════════════════════════════════════╝\n" + RESET);

        // Display Original Message
        System.out.println(CYAN + "📜 Original Message:" + RESET + " " + message);
        System.out.println("\n" + BOLD + YELLOW + "🔢 ASCII Representation:" + RESET);
        for (Integer val : converter.StringToAscii(message)) {
            System.out.print(GREEN + val + " " + RESET);
        }
        System.out.println("\n");

        // Encrypt the ASCII values
        ArrayList<BigInteger> cipher = encryptor.Encrypt(converter.StringToAscii(message), e, n);
        System.out.println(BOLD + RED + "🔐 Ciphertext (Encrypted ASCII):" + RESET);
        for (BigInteger c : cipher) {
            String encodedCipher = converter.encode(c);
            System.out.println(GREEN + (encodedCipher.length() >= 10 ? encodedCipher.substring(0, 10) + "..." : encodedCipher) + RESET);
        }
        System.out.println("\n");

        // Decrypt the Ciphertext back to ASCII
        ArrayList<BigInteger> decryptedAscii = decryptor.Decrypt(cipher, d, n);
        System.out.println(BOLD + YELLOW + "🔓 Decrypted ASCII Values:" + RESET);
        for (BigInteger val : decryptedAscii) {
            System.out.print(CYAN + val + " " + RESET);
        }
        System.out.println("\n");

        // Convert ASCII back to readable text
        String decryptedMessage = converter.AsciiToString(decryptedAscii);
        System.out.println(BOLD + GREEN + "📝 Decrypted Message:" + RESET + " " + decryptedMessage);
        
        System.out.println("\n" + BOLD + BLUE + "✅ Process Completed Successfully! 🎉" + RESET);
    }
}
