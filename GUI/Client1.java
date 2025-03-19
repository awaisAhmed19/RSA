
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class Client1 {
    private static PrivateKey privateKey;  // This client has the key

    public static void main(String[] args) {
        try {
            generateRSAKeys();
            Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("1");  // Identify as Client 1
            String encryptedMessage = in.readLine();
            System.out.println("Received: " + encryptedMessage);

            String decryptedMessage = decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted: " + decryptedMessage);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
    }

    private static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }
}
