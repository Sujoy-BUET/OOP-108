import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server Started..");
        Socket socket = serverSocket.accept();
        System.out.println("Client connected..");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        while(true){
            try {
                //read from client...
                Object cMsg = ois.readObject();
                System.out.println("From Client: " + (String) cMsg);

                String serverMsg = (String) cMsg;
                serverMsg = serverMsg.toUpperCase();

                //send to client..
                oos.writeObject(serverMsg);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}