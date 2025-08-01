package mytest;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import util.SocketWrapper;

public class Server{
    private ServerSocket serverSocket;
    HashMap<String,SocketWrapper> clientMap = new HashMap<>();
    Server(){
        try{
            serverSocket = new ServerSocket(44444);
            while(true){
                Socket socket =  serverSocket.accept();
                System.out.println("Client connected");
                serve(socket);
            }
        }catch(Exception e){
            System.out.println(e);
        }   
    }
    public void serve(Socket socket) throws IOException, ClassNotFoundException{
        SocketWrapper socketWrapper = new SocketWrapper(socket);
        String name = (String)socketWrapper.read();
        clientMap.put(name,socketWrapper);
        new ReadThreadServer(clientMap,socketWrapper,name);
    }
    public static void main(String[] args) {
        new Server();
    }
}