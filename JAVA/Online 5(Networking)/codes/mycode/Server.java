package mycode;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import tcpforward.Message;
import util.SocketWrapper;

public class Server {
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;

    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(44444);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        SocketWrapper socketWrapper = new SocketWrapper(clientSocket);
        try{
            while(true){
               Object req =socketWrapper.read();
               String request=null;
                if(req instanceof String) request  = (String)req;
                if(request.equals("Join")){
                     String clientName =(String)socketWrapper.read();
                    clientMap.put(clientName, socketWrapper);
                    socketWrapper.write("Joined");
                }else if(request.equals("AcquireList")){
                    ArrayList<String> arr = new ArrayList<>();
                    for(String s:clientMap.keySet()){
                        arr.add(s);
                    }
                    socketWrapper.write(arr);
                }else if(request.equals("TransmitOne")){
                    Object ob = socketWrapper.read();
                    if(ob instanceof Message){
                        Message msg = (Message)ob;
                        String s = msg.getTo();
                        SocketWrapper dest = clientMap.get(s);
                        if(dest!=null){
                            dest.write(ob);
                        }
                    }
                }else if(request.equals("Broadcast")){
                    for(String s:clientMap.keySet()){
                        SocketWrapper sc = clientMap.get(s);
                        if(sc!=null){
                            sc.write(socketWrapper.read());
                        }
                    }
                }else{
                    if(req instanceof Message){
                        Message msg = (Message)req;
                        System.out.println(msg.getFrom()+": "+msg.getTo());
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Server();
    }
}
