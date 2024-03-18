package MAS_IDS;

import MAS_IDS.Agents.ManagerAgent;
import MAS_IDS.PlatfromObjects.Message;
import MAS_IDS.PlatfromObjects.Message2;
import MAS_IDS.PlatfromObjects.MessageContainer;
import jade.core.Profile;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    static Socket socket;
    static ServerSocket serverSocket;
    static InputStreamReader inputStreamReader;
    static BufferedReader bufferedReader;
    static String transferedData;

    public static void main(String[] args) {


        int x = 0;



        try {
            serverSocket = new ServerSocket(7800);
            while (true){
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                transferedData = bufferedReader.readLine();
                System.out.println("Android : "+transferedData);
                if (transferedData.equals("Start")){
                    PlatformSharedInformation.profile.setParameter(Profile.MAIN_HOST, "localhost");
                    PlatformSharedInformation.profile.setParameter(Profile.GUI,"true");
                    PlatformSharedInformation.containerController = PlatformSharedInformation.runtime.createMainContainer(PlatformSharedInformation.profile);
                    PlatformSharedInformation.startTime= PlatformSharedInformation.methode();

                    sendMessageToMobile("LTime:"+ PlatformSharedInformation.startTime2);





                    AgentController agentController = null;
                    try {

                        agentController =   PlatformSharedInformation.containerController.createNewAgent("MAS_IDS.Agents.ManagerAgent","MAS_IDS.Agents.ManagerAgent",null);
                        agentController.start();

                        agentController =   PlatformSharedInformation.containerController.createNewAgent("MAS_IDS.Agents.ClassifAgent","MAS_IDS.Agents.ClassifAgent",null);
                        agentController.start();



                    } catch (StaleProxyException e) {
                        e.printStackTrace();
                    }
                }

                if(transferedData.equals("getAgents")){

                    sendMessageToMobile(ContainersStats());

                }

                if(transferedData.equals("shutdown")){
                    //shutitdown;
                    System.exit(0);
                }

                if(transferedData.equals("retrain")){
                    //retrain;
                    ManagerAgent.needtoupadte=true;

                }

                if(transferedData.equals("getMessages")){
                    sendMessageToMobile(SharedMessagesBetweenAgents());
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

                    ACLMessage msg = MAS_IDS.Agents.ManagerAgent.aclMessages.get(0);

                    for(int i=1;i<MAS_IDS.Agents.ManagerAgent.aclMessages.size();i++){
                        System.out.println("MessageID"+i+":\nSender:"+msg.getSender()+"\n-Receivers:"+msg.getAllReceiver()+"\nContent:"+msg.getContent()+"\n");
                    }
                }
            },15000);

*/






        }

        //this method send a message to the connected android application UI
        static void sendMessageToMobile(String message) throws IOException {

        Socket socket1 = new Socket("192.168.43.206",7801);

        OutputStream outputStream = socket1.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it

        PrintWriter pw = new PrintWriter(socket1.getOutputStream());
        pw.write(message);
        pw.flush();
        pw.close();

        System.out.println("SEND");
        socket1.close();
    }

    //this method prepare all the needed information from all running containers
    public static String ContainersStats(){

        //String x="GA:N_1,SS_A,SSN_A,SA_A,NP_120,NA_60|"+"N_2,SS_A,SSN_A,SA_A,NP_130,NA_60|";
        String message ="GA:";




        for(int i=0;i<ManagerAgent.containers.size();i++){
            MessageContainer messageContainer = ManagerAgent.containers.get(i).getThemAll();
            message+=messageContainer.toSend()+"|";
        }

        return message;
    }


    //this method  prepare all the shared messages between agents
    public static  String SharedMessagesBetweenAgents(){

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



