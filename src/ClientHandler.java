import java.io.*;
import java.net.Socket;
import java.util.Set;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Set<ClientHandler> clients;

    public ClientHandler(Socket socket, Set<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;

        try {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage() {
        writer.println(msg);
    }



    @Override
    public void run() {
        try {
            String name = reader.readLine();
            broadCast(name + " joined the chat");
            String msg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void broadCast(String name) {
        for (ClientHandler client : clients) {
            client.sendMessage(msg);
        }
    }
}
