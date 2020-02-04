import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static int p1;
    private static ServerSocket serverSocket;
    private static Socket clientSocket = null;

    public void setPort(int port){
        p1=port;
    }
    public static void main(String[] args) throws IOException {

        try {
            serverSocket = new ServerSocket(p1);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new Client.CLIENTConnection(clientSocket));

                t.start();

            } catch (Exception e) {

                System.err.println("Error in connection attempt.");
            }
        }
    }
}