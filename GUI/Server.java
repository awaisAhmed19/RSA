
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class Server {
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void main(String[] args) {
        try {
            generateRSAKeys();
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket, publicKey, privateKey).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateRSAKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private PublicKey publicKey;
        private PrivateKey privateKey;

        public ClientHandler(Socket socket, PublicKey publicKey, PrivateKey privateKey) {
            this.socket = socket;
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String clientType = in.readLine();  // Read client type (1, 2, or 3)

                String message = "Confidential Message: The meeting is at 3 PM.";
                String encryptedMessage = encrypt(message, publicKey);

                System.out.println("Sending to client " + clientType + ": " + encryptedMessage);
                out.println(encryptedMessage);

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String encrypt(String message, PublicKey publicKey) throws Exception {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        }
    }
}
