package src;

import Utils.BaseConvertor;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class Main {

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

        // Convert message to ASCII values
        ArrayList<Integer> asciiValues = converter.StringToAscii(message);
        System.out.println("\n🔹 Original Message: " + message);
        System.out.println("\n🔢 ASCII Representation:");
        for (Integer val : asciiValues) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        // Encrypt the ASCII values
        ArrayList<BigInteger> cipher = encryptor.Encrypt(asciiValues, e, n);
        System.out.println("🔐 Ciphertext (Encrypted ASCII):");
        for (BigInteger c : cipher) {
            String encodedCipher = converter.encode(c);git
            System.out.println(encodedCipher.length() >= 10 ? encodedCipher.substring(0, 10) + "..." : encodedCipher);
 
        }
        System.out.println("\n");

        // Decrypt the Ciphertext back to ASCII
        ArrayList<BigInteger> decryptedAscii = decryptor.Decrypt(cipher, d, n);
        System.out.println("🔓 Decrypted ASCII Values:");
        for (BigInteger val : decryptedAscii) {
            System.out.print(val + " ");
        }
        System.out.println("\n");

        // Convert ASCII back to readable text
        String decryptedMessage = converter.AsciiToString(decryptedAscii);
        System.out.println("📝 Decrypted Message: " + decryptedMessage);
    }
}
