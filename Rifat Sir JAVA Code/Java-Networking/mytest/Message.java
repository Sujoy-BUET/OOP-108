package mytest;

import java.io.Serializable;

public class Message implements Serializable{
    private String From,To,Text;
    public void setTo(String name) {
        To = name;
    }
    public void setFrom(String name){
        From = name;
    }
    public void setMessage(String msg){
        Text = msg;
    }
    public String getTo(){
        return To;
    }
    public String getFrom(){
        return From;
    }
    public String getMessage(){
        return Text;
    }
}