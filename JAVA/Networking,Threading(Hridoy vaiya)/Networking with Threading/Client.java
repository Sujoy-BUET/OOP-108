import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


class OutputThread implements Runnable{
    ObjectInputStream ois;
    
    OutputThread(ObjectInputStream ois) {
        this.ois = ois;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object fromServer = ois.readObject();
                System.out.println("From Server: " + (String) fromServer);
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

class InputThread implements Runnable{
    ObjectOutputStream oos;
    Scanner sc = new Scanner(System.in);
    InputThread(ObjectOutputStream oos){
        this.oos = oos;
        new Thread(this).start();
    }
    @Override
    public void run() {
        while(true){
            String message = sc.nextLine();
            if(message.equals("exit")){
                System.out.println("Exiting Client..");
                System.exit(0);
            }
            try {
                oos.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started..");
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println("Client Connected..");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        
        
            
            new InputThread(oos);
            new OutputThread(ois);
        
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
