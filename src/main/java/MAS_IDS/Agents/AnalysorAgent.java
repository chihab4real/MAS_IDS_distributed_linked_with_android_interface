package MAS_IDS.Agents;

import MAS_IDS.PlatfromObjects.*;
import com.mongodb.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import weka.core.Instance;

import java.util.ArrayList;


public class AnalysorAgent extends Agent {



    PlatformClassifier DT,SVM,NN;

    @Override
    protected void setup() {
        ArrayList<Attack> attacks = ClassifAgent.attacks;

        DT = ClassifAgent.DT;
        SVM= ClassifAgent.SVM;
        NN = ClassifAgent.NN;

        String containerID = getMyID(getAID().getLocalName());

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage received = receive();
                if(received!=null){

                    if(received.getContent().equals("Update")){
                        //get the newer version of classfiers after updating them
                        DT = ClassifAgent.DT;
                        SVM= ClassifAgent.SVM;
                        NN = ClassifAgent.NN;
                    }

                    if(received.getContent().equals("Check")){

                        //Check message
                        SendMessage("Check_OK","SubManagerAgent_Container"+String.valueOf(containerID));

                        /*
                        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
                        toSend.setContent("Check_OK");
                        AID receiverAgent = null;
                        receiverAgent = new AID("SubManagerAgent_Container"+String.valueOf(containerID), AID.ISLOCALNAME);
                        toSend.addReceiver(receiverAgent);
                        send(toSend);
                        try {
                            ManagerAgent.addMessage(new Message(toSend.getSender().getLocalName(),"SubManagerAgent_Container"+String.valueOf(containerID),toSend.getContent()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }*/
                    }
                }



                //Check with other agents after a specific number of classified packets
                if(ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getPacketClassified().size()>=5 && !ManagerAgent.containers.get(Integer.parseInt(containerID)-1).isInformed()) {
                    if(!ManagerAgent.containers.get(Integer.parseInt(containerID)-1).isAgentInformer()){

                        ACLMessage aclMessage = SendMessage("Check50_A" + containerID,"MAS_IDS.Agents.ManagerAgent");
                        ManagerAgent.addMessage(new Message(aclMessage.getSender().getLocalName(), "MAS_IDS.Agents.ManagerAgent", aclMessage.getContent()));
                        ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setAgentInformer(true);




                        /*
                        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
                        toSend.setContent("Check50_A" + containerID);
                        AID dest = null;
                        dest = new AID("MAS_IDS.Agents.ManagerAgent", AID.ISLOCALNAME);
                        toSend.addReceiver(dest);
                        send(toSend);
                        try {
                            ManagerAgent.addMessage(new Message(toSend.getSender().getLocalName(),"MAS_IDS.Agents.ManagerAgent",toSend.getContent()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }





                        Message messageListe;
                        messageListe = new Message(msg.getSender().getLocalName(), "MAS_IDS.Agents.ManagerAgent", msg.getContent());
                        ManagerAgent.addMessage(messageListe);

                        ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setAgentInformer(true);




                      */

                    }

                    ACLMessage received_after = receive();
                    if (received_after!=null){
                        if (received_after.getContent().equals("netst")){
                            ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setCuurentstate(ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getPacketClassified().size());
                            block();
                        }

                        if(received_after.getContent().contains("ADT_")){
                            ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setInformed(true);
                            System.out.println("Anomaly/attack on "+received_after.getContent().replace("ADT_","")+"machines");


                        }
                        block();
                    }
                }
                if(!ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getSniffedPackets().isEmpty()){

                    PacketSniffer packetSniffer = ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getSniffedPackets().get(0);


                    try {
                        DetectType(attacks,packetSniffer,DT,SVM,NN);


                        ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getSniffedPackets().remove(packetSniffer);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


    }
    private ACLMessage SendMessage(String content, String AgentAID){

        //Check message
        ACLMessage toSend = new ACLMessage(ACLMessage.INFORM);
        toSend.setContent(content);
        AID receiverAgent = null;
        receiverAgent = new AID(AgentAID, AID.ISLOCALNAME);
        toSend.addReceiver(receiverAgent);
        send(toSend);
        try {
            ManagerAgent.addMessage(new Message(toSend.getSender().getLocalName(),AgentAID,toSend.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toSend;
    }

    public String getMyID(String AID){
        return AID.replace("AnalysorAgent_Container","");
    }





    public void DetectType(ArrayList<Attack> attacks, PacketSniffer packetTest, PlatformClassifier DT, PlatformClassifier SVM, PlatformClassifier NN) throws Exception{

        String containerID = getMyID(getAID().getLocalName());

        double resultj48 = DT.getClassifier().classifyInstance(packetTest.getInstance());
        double resultjsvm = SVM.getClassifier().classifyInstance(packetTest.getInstance());
        double resultjnn = NN.getClassifier().classifyInstance(packetTest.getInstance());
        Attack attackj48 = attacks.get((int)resultj48);
        Attack attacksvm = attacks.get((int)resultjsvm);
        Attack attacknn = attacks.get((int)resultjnn);


        System.out.println("\n\nPacket : "+"--:\t"+packetTest.getInstance()+"\nDetected as :\n----------------");

        System.out.println("Decesion tree: "+attacks.get((int) resultj48).getName()+"  ["+attacks.get((int)resultj48).getCategory()+"]");

        System.out.println("SVM: "+attacks.get((int) resultjsvm).getName()+"  ["+attacks.get((int)resultjsvm).getCategory()+"]");
        System.out.println("NN: "+attacks.get((int) resultjnn).getName()+"  ["+attacks.get((int)resultjnn).getCategory()+"]\n\n");

        double finalClass= getFinalClass(DT,SVM,NN,attackj48,attacksvm,attacknn);

        System.out.println("Final decision : "+attacks.get((int)finalClass).getName());

        Instance instance = packetTest.getInstance();
        instance.setValue(26,finalClass);
        PacketDetected packetDetected = new PacketDetected(instance);
        packetDetected.setCategory(attacks.get((int)finalClass).getCategory());
        System.out.println("\n\nCATE:"+packetDetected.getCategory());
        packetDetected.setBywho(getByWho(attacks.get((int)finalClass).getName(),attackj48,attacksvm,attacknn));
        ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getPacketClassified().add(packetDetected);
        sendPackettoDB(packetDetected);

        /*

        System.out.println("\n----------------------------------------------\n\n"+getAID().getLocalName()+": \n");

        System.out.println(packetTest.getInstance());
        System.out.println("DT:\t"+MAS_IDS.Agents.ClassifAgent.j48.classifyInstance(packetTest.getInstance()));
        System.out.println("NN:\t"+MAS_IDS.Agents.ClassifAgent.multilayerPerceptron.classifyInstance(packetTest.getInstance()));
        System.out.println("SVM:\t"+MAS_IDS.Agents.ClassifAgent.smOreg.classifyInstance(packetTest.getInstance()));
        String containerID = getMyID(getAID().getLocalName());
        ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
        MAS_IDS.Agents.ManagerAgent.packetSolveds.add(new MAS_IDS.PlatfromObjects.PacketSolved(packetTest.getInstance(),packetTest.getInstance(),true));
        MAS_IDS.Agents.ManagerAgent.containers.get(Integer.parseInt(containerID)-1).getPacketsDetected().remove(0);

        msg.setContent("PCT"+"_OK");
        AID dest = null;
        dest = new AID("SnifferAgent_Container"+containerID,AID.ISLOCALNAME);
        msg.addReceiver(dest);
        send(msg);

        MAS_IDS.PlatfromObjects.Message messageListe;
        messageListe = new MAS_IDS.PlatfromObjects.Message(msg.getSender().getLocalName(),"SnifferAgent_Container"+containerID,msg.getContent());
        MAS_IDS.Agents.ManagerAgent.addMessage(messageListe);

        //System.out.println("FINAAAAAAAAAL");*/



    }



    public  double getFinalClass(PlatformClassifier DT, PlatformClassifier SVM, PlatformClassifier NN, Attack j48, Attack svm, Attack nn){



        ArrayList<Attack> attacks = new ArrayList<>();
        attacks.add(j48);
        attacks.add(svm);
        attacks.add(nn);

        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Double> scores = new ArrayList<>();
        ArrayList<PlatformClassifier> clsis = new ArrayList<>();
        clsis.add(DT);
        clsis.add(SVM);
        clsis.add(NN);


        for(int i=0;i<attacks.size();i++){
            if(!strings.isEmpty() && strings.contains(attacks.get(i).getID())){
                double x = scores.get(strings.indexOf(attacks.get(i).getID()));

                scores.set(strings.indexOf(attacks.get(i).getID()),x+clsis.get(i).getEvaluation().fMeasure(1));
                x=0;

            }else{
                strings.add(attacks.get(i).getID());

                scores.add(clsis.get(i).getEvaluation().fMeasure(1));

            }
        }

        double max=-1;
        double index=-1;
        for(int i=0;i<scores.size();i++){
            if(scores.get(i)>max){
                max=scores.get(i);
                index=i;
            }
        }

        return Double.parseDouble(strings.get((int)index));
    }

    public String getByWho(String result, Attack j48, Attack svm, Attack nn){
        String x="";
        if(result.equals(j48.getName())){
            x+="DT,";
        }

        if(result.equals(svm.getName())){
            x+="SVM,";
        }

        if(result.equals(nn.getName())){
            x+="NN,";
        }

        System.out.println("\n\nBYWHO:"+x);
        return x;
    }

    public static void sendPackettoDB(PacketDetected packetDetected)throws Exception{
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("Test");

        DBCollection collection = database.getCollection("PacketsDetected");


        DBObject dbObject = packetDetected.toDBObject();

        /*List<Integer> books = Arrays.asList(27464, 747854);

        DBObject person = new BasicDBObject("_id", "jo")
                .append("name", "Jo Bloggs")
                .append("address", new BasicDBObject("street", "123 Fake St")
                        .append("city", "Faketon")
                        .append("state", "MA")
                        .append("zip", 12345))
                .append("books", books);*/


        collection.insert(dbObject);
        System.out.println("Packet Detectd and saved as  "+dbObject.get("class"));


    }


}
