import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ServerThread implements Runnable{
    Socket socket;
    ServerThread(Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            //read from client...
            while(true){
                Object cMsg = ois.readObject();
                System.out.println("From Client: " + (String) cMsg);

                String serverMsg = (String) cMsg;
                serverMsg = serverMsg.toUpperCase();

                //send to client..
                oos.writeObject(serverMsg);
                oos.flush();
            }
            

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("Server Started..");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");
            new ServerThread(socket); 
            
        }
        // serverSocket.close(); // Unreachable code removed
    }
}
