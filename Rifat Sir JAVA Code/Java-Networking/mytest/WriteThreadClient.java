package mytest;

import java.util.Scanner;

import util.SocketWrapper;

public class WriteThreadClient implements Runnable{
    String name;
    SocketWrapper socketWrapper;
    WriteThreadClient(SocketWrapper socketWrapper,String name){
        this.socketWrapper = socketWrapper;
        this.name = name;
        new Thread(this).start();
    }
    public void run(){
        Scanner sc = null;
        try{
            while(true){
            sc = new Scanner(System.in);
            Message msg = new Message();
            System.out.print("Message to: ");
            String client = sc.nextLine();
            msg.setFrom(name);
            msg.setTo(client);
            System.out.println("Message: ");
            String txt = sc.nextLine();
            msg.setMessage(txt);
            socketWrapper.write(msg);
        }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}