import java.io.*;
import java.net.*;

public class Server {
    private Socket s = null;
    private ServerSocket ss = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private BufferedReader reader = null;
    public Server(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started. Waiting for a client...");

            s = ss.accept();
            System.out.println("Client accepted!");

            in = new DataInputStream(new BufferedInputStream(s.getInputStream())); // Receive data
            out = new DataOutputStream(s.getOutputStream()); // Send data
            reader = new BufferedReader(new InputStreamReader(System.in)); // Read user input
            
            // **Thread to read incoming messages from the client**
            new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = in.readUTF();
                        System.out.println("\nClient: " + clientMessage);
                        System.out.print("You: "); // Show prompt again
                    }
                } catch (IOException e) {
                    System.out.println("Client disconnected.");
                }
            }).start();

            // **Main thread handles user input**
            while (true) {
                System.out.print("You: ");
                String sm = reader.readLine();
                out.writeUTF(sm);
                out.flush(); // Ensure message is sent immediately
                if (sm.equalsIgnoreCase("Over")) break; // Exit condition
            }

            // Close resources
            in.close();
            out.close();
            s.close();
            ss.close();
            reader.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        new Server(5000);
    }
}
