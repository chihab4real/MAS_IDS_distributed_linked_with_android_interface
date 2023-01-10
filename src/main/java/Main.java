import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    static Socket s;
    static ServerSocket ss;
    static InputStreamReader isr;
    static BufferedReader br;
    static String message;

    public static void main(String[] args) {

        int x = 0;



        try {
            ss = new ServerSocket(7800);
            while (true){
                s = ss.accept();
                isr = new InputStreamReader(s.getInputStream());
                br = new BufferedReader(isr);
                message = br.readLine();
                System.out.println("Android : "+message);
                if (message.equals("Start")){
                    PlatformPara.profile.setParameter(Profile.MAIN_HOST, "localhost");
                    PlatformPara.profile.setParameter(Profile.GUI,"true");
                    PlatformPara.containerController = PlatformPara.runtime.createMainContainer(PlatformPara.profile);
                    PlatformPara.startTime=PlatformPara.methode();

                    //
                    sendMSG("LTime:"+PlatformPara.startTime2);





                    AgentController agentController = null;
                    try {

                        agentController =   PlatformPara.containerController.createNewAgent("ManagerAgent","ManagerAgent",null);
                        agentController.start();

                        agentController =   PlatformPara.containerController.createNewAgent("ClassifAgent","ClassifAgent",null);
                        agentController.start();



                    } catch (StaleProxyException e) {
                        e.printStackTrace();
                    }
                }

                if(message.equals("getAgents")){

                    sendMSG(meth());

                }

                if(message.equals("shutdown")){
                    //shutitdown;
                    System.exit(0);
                }

                if(message.equals("retrain")){
                    //retrain;
                    ManagerAgent.needtoupadte=true;

                }

                if(message.equals("getMessages")){
                    sendMSG(getMessages());
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }



            /*
            Timer timer = new Timer();
        AgentController finalAgentController = agentController;
        timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    ACLMessage msg = ManagerAgent.aclMessages.get(0);

                    for(int i=1;i<ManagerAgent.aclMessages.size();i++){
                        System.out.println("MessageID"+i+":\nSender:"+msg.getSender()+"\n-Receivers:"+msg.getAllReceiver()+"\nContent:"+msg.getContent()+"\n");
                    }
                }
            },15000);

*/






        }

    static  void sendMSG(String message) throws IOException {

        Socket socket = new Socket("192.168.43.206",7801);

        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it





        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.write(message);
        pw.flush();
        pw.close();

        System.out.println("SEND");
        socket.close();
    }

    public static String meth(){

        //String x="GA:N_1,SS_A,SSN_A,SA_A,NP_120,NA_60|"+"N_2,SS_A,SSN_A,SA_A,NP_130,NA_60|";
        String message ="GA:";




        for(int i=0;i<ManagerAgent.containers.size();i++){
            MessageContainer messageContainer = ManagerAgent.containers.get(i).getThemAll();
            message+=messageContainer.toSend()+"|";
        }

        return message;
    }

    public static  String getMessages(){

        String x = "GM:";

        for(int i=0;i<ManagerAgent.messages.size();i++){
            Message message = ManagerAgent.messages.get(i);
            Message2 message2 = new Message2();
            message2.setSender(message.getSender());
            message2.setReciever(message.getReciever());
            message2.setContent(message.getContent());
            message2.setTime(""+message.getTime().getHour()+":"+message.getTime().getMinute()+":"+message.getTime().getSecond());
            String word=message2.getSender()+","+message2.getReciever()+","+message2.getContent()+","+message2.getTime()+",";

            x+=word+"|";

        }

        return x;
    }

    }



