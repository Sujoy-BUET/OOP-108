package mytest;



import util.SocketWrapper;

public class ReadThreadClient implements Runnable{
    private SocketWrapper socketWrapper;
    ReadThreadClient(SocketWrapper socketWrapper){
        this.socketWrapper = socketWrapper;
        new Thread(this).start();
    }
    @Override
    public void run(){
        try{
            while(true){
                Object obj = socketWrapper.read();
                if(obj instanceof Message){
                    Message msg = (Message)obj;
                    System.out.println(msg.getFrom()+" to "+msg.getTo()+ " : "+ msg.getMessage());
                }
            }   
        }catch(Exception e){
            System.out.println(e);
        }
    }
}