package Sockets;
import java.io.*;
import java.net.*;
import java.math.*;
import java.util.ArrayList;
import Utils.BaseConvertor;
import src.RSAEncryptor;
import src.RSADecryptor;

public class Client {
    private Socket socket = null;
    private BufferedReader userInput = null;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private BigInteger n, e;
    private RSAEncryptor encryptor = new RSAEncryptor();
    private RSADecryptor decryptor = new RSADecryptor();
    private BaseConvertor base = new BaseConvertor();

    public Client(String address, int port) {
        try {
            // Connect to server
            socket = new Socket(address, port);
            System.out.println("Connected to Server!");

            // Setup I/O streams
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            userInput = new BufferedReader(new InputStreamReader(System.in));

            // Read RSA keys from the server
            n = new BigInteger(in.readUTF());
            e = new BigInteger(in.readUTF());
            System.out.println("Received Public Key: (e=" + e + ", n=" + n + ")");

            // Start a separate thread for receiving messages
            new Thread(this::receiveMessages).start();

            // Handle user input
            sendMessages();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            closeConnections();
        }
    }

    // Method to handle sending encrypted messages
    private void sendMessages() {
        try {
            while (true) {
                System.out.print("You: ");
                String message = userInput.readLine();
                
                // Encrypt message
                ArrayList<Integer> asciiMessage = base.StringToAscii(message);
                ArrayList<BigInteger> encryptedMessage = encryptor.Encrypt(asciiMessage, e, n);

                // Send encrypted data
                for (BigInteger c : encryptedMessage) {
                    out.writeUTF(base.encode(c)); 
                }
                out.writeUTF("END"); // Signal end of message
                out.flush();

                // Check for exit condition
                if (message.equalsIgnoreCase("Over")) break;
            }
        } catch (IOException ex) {
            System.out.println("Error sending message: " + ex.getMessage());
        }
    }

    // Method to receive and decrypt messages
    private void receiveMessages() {
        try {
            while (true) {
                String encryptedPart;
                ArrayList<BigInteger> cipher = new ArrayList<>();

                // Receive encrypted message parts
                while (!(encryptedPart = in.readUTF()).equals("END")) {
                    cipher.add(base.decode(encryptedPart));
                }

                // Decrypt message
                ArrayList<Integer> decryptedAscii = decryptor.Decrypt(cipher, d, n);
                String decryptedMessage = base.AsciiToString(decryptedAscii);

                System.out.println("\nServer (decrypted): " + decryptedMessage);
                System.out.print("You: "); // Show prompt again
            }
        } catch (IOException ex) {
            System.out.println("Connection closed.");
        }
    }

    // Close all resources
    private void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (userInput != null) userInput.close();
        } catch (IOException ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }

    public static void main(String args[]) {
        new Client("127.0.0.1", 5000);
    }
}
