import jade.lang.acl.ACLMessage;

import java.time.LocalDateTime;

public class Message2 {
    private String Sender;
    private String Reciever;
    private String Content;
    private String Time;

    Message2(){

    }

    Message2(String sender, String reciever, String content,String time){
        this.Content = content;
        this.Reciever = reciever;
        this.Sender = sender;
        this.Time = time;
    }

    Message2(ACLMessage aclMessage){
        this.Sender = aclMessage.getSender().getLocalName();
        this.Content = aclMessage.getContent();


    }
    public String PrintMessage(){
        return "Sender: "+this.Sender+"\nReciever: "+this.Reciever+"\nContent: "+this.Content;
    }


    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
