package mycode;

import util.SocketWrapper;

import java.util.ArrayList;
import java.util.Scanner;

import tcpdiff.WriteThreadClient;

public class Client {
    SocketWrapper socketWrapper = null;
    public Client(String serverAddress, int serverPort) {
        Scanner scanner = null;
        
        try {
            while(true){
                System.out.println("1. Join");
                System.out.println("2. AcquireList");
                System.out.println("3.TransmitOne");
                System.out.println("4. BroadCast");
                scanner = new Scanner(System.in);
                int type = scanner.nextInt();
                scanner.nextLine();
                if(type==1){
                    socketWrapper = new SocketWrapper(serverAddress, serverPort);
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    socketWrapper.write("Join");
                    socketWrapper.write(name);
                    String msg= (String) socketWrapper.read();
                    if(msg.equals("Joined")){
                        System.out.println("joined");
                    }else{
                        System.out.println("Couldn't join");
                        break;
                    }
                }else if(type==2){
                    socketWrapper.write("AcquireList");
                    Object ob = socketWrapper.read();
                    if(ob instanceof ArrayList){
                        ArrayList<String> arr = (ArrayList)ob;
                        System.out.println("Clients: ");
                        for(String s:arr){
                            System.out.println(s);
                        }
                    }
                }else if(type==3){
                    socketWrapper.write("TrasmitOne");
                    new WriteThreadClient(socketWrapper, serverAddress);
                }else if(type==4){
                    socketWrapper.write("BroadCast");
                }else{
                    System.out.println("Invalid Input");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            //scanner.close();
        }
    }

    public static void main(String args[]) {
        String serverAddress = "127.0.0.1";
        int serverPort = 44444;
        new Client(serverAddress, serverPort);
    }
}
