import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            // Создаем новый поток, который будет слушать сообщения от сервера
            Thread receiverThread = new Thread(() -> {
                String message;
                try {
                    while (true) {
                        message = reader.readLine();
                        if(message != null){
                            System.out.println(message);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiverThread.start();

            String message;
            while (true) {
                System.out.print("Enter message (or 'exit' to quit): ");
                message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
