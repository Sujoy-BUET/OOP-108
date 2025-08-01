package mytest;

import java.io.IOException;
import java.util.HashMap;
import util.SocketWrapper;

public class ReadThreadServer implements Runnable{
    HashMap<String,SocketWrapper> clientMap;
    private SocketWrapper socketWrapper;
    ReadThreadServer(HashMap<String,SocketWrapper> hp,SocketWrapper socketWrapper,String name){
        this.clientMap = hp;
        this.socketWrapper = socketWrapper;
        new Thread(this).start();
    }

    public void run(){
        try{
            while(true){
                Object obj = socketWrapper.read();
                if(obj instanceof Message){
                    String to = ((Message)obj).getTo();
                    SocketWrapper dest = clientMap.get(to);
                    if(dest!=null){
                        dest.write(obj);
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}