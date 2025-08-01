package mytest;

import java.util.Scanner;

import util.SocketWrapper;

public class Client{    
    Client(){
        Scanner sc = null;
        try{
            System.out.print("Your name: ");
            sc = new Scanner(System.in);
            String name = sc.nextLine();
            SocketWrapper socketWrapper = new SocketWrapper("127.0.0.1",44444);
            socketWrapper.write(name);
            new ReadThreadClient(socketWrapper);
            new WriteThreadClient(socketWrapper, name);
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            // if(sc!=null){
            //     sc.close();
            // }
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}