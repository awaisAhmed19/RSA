
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class RSAClientGUI extends JFrame {
    private JTextArea outputArea;
    private JButton connectButton;

    public RSAClientGUI() {
        setTitle("RSA Client 1");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea));

        connectButton = new JButton("Connect to Server");
        add(connectButton);

        connectButton.addActionListener(e -> connectToServer());

        setVisible(true);
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 5000);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("1");  // Identify as Client 1
            String encryptedMessage = in.readLine();
            outputArea.setText("Received: " + encryptedMessage);

            socket.close();
        } catch (Exception e) {
            outputArea.setText("Error connecting to server.");
        }
    }

    public static void main(String[] args) {
        new RSAClientGUI();
    }
}
