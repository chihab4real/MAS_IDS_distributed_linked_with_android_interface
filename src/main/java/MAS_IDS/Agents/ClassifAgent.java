package MAS_IDS.Agents;

import MAS_IDS.PlatfromObjects.Attack;
import MAS_IDS.PlatfromObjects.Clsi;
import MAS_IDS.PlatfromObjects.Message;
import MAS_IDS.PlatfromObjects.PacketDT;
import com.mongodb.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClassifAgent extends Agent {

    public static ArrayList<Attack> attacks = new ArrayList<>();

    public static Clsi DT;
    public static Clsi SVM;
    public static Clsi NN;

    private int ok1 = 0;
    private int ok2 = 0;
    private int ok3 = 0;

    private String lastTrainingtime;
    @Override
    protected void setup() {

        try {
            Classiy();
        } catch (Exception e) {
            e.printStackTrace();
        }

        addBehaviour(new TickerBehaviour(this,3600000) {

            @Override
            protected void onTick() {
                int x=0;
                try {
                    x =UpdateC();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(x==1){
                    for(int i=0;i<ManagerAgent.containers.size();i++){
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.setContent("Update");
                        AID dest = new AID("AnalysorAgent_Container"+(i+1),AID.ISLOCALNAME);
                        msg.addReceiver(dest);
                        send(msg);
                        try {
                            ManagerAgent.addMessage((new Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+(i+1),msg.getContent())));
                            //MAS_IDS.PlatformPara.NotifyMessages(new MAS_IDS.PlatfromObjects.Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+(i+1),msg.getContent()),0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        });

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if(aclMessage!=null){
                    if (aclMessage.getContent().equals("update")){
                        try {
                            Classiy();
                            for(int i=0;i<ManagerAgent.containers.size();i++){
                                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                msg.setContent("Update");
                                AID dest = new AID("AnalysorAgent_Container"+(i+1),AID.ISLOCALNAME);
                                msg.addReceiver(dest);
                                send(msg);
                                try {
                                    ManagerAgent.addMessage((new Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+(i+1),msg.getContent())));
                                    //MAS_IDS.PlatformPara.NotifyMessages(new MAS_IDS.PlatfromObjects.Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+(i+1),msg.getContent()),0);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    public void CallDT(Instances TrainDataDT) throws Exception {


        J48 j48 = new J48();
        j48.buildClassifier(TrainDataDT);

        DT = new Clsi("DT", j48, TrainDataDT);

        System.out.println("DT DONE-----");
        System.out.println("F-Measure :" + DT.getEvaluation().fMeasure(1));
        /*System.out.println("Precision : "+DT.getEvaluation().precision(1));
        System.out.println("Recall: "+DT.getEvaluation().recall(1));
        System.out.println("Error rate: "+DT.getEvaluation().errorRate());
        System.out.println("% Correct: "+DT.getEvaluation().pctCorrect());
        System.out.println("% Incorrect:  "+DT.getEvaluation().pctIncorrect());*/
        System.out.println("----------------------\n");
        sendMSG("DTOK,"+TrainDataDT.size()+","+
                DT.getEvaluation().fMeasure(1)+","+
                DT.getEvaluation().precision(1)+","+
                DT.getEvaluation().falsePositiveRate(1)+","+
                DT.getEvaluation().kappa()+",");

        //sendMSG("DT,TR"+TrainDataDT.size()+",FM"+DT.getEvaluation().fMeasure(1)+",PR"+DT.getEvaluation().precision(1));
        ok1 = 1;

    }

    void CallNN(Instances TrainDataSVM) throws Exception {


        MultilayerPerceptron multilayerPerceptron = new MultilayerPerceptron();

        multilayerPerceptron.setLearningRate(0.1);
        multilayerPerceptron.setMomentum(0.2);
        multilayerPerceptron.setTrainingTime(2000);
        multilayerPerceptron.setHiddenLayers("3");
        multilayerPerceptron.buildClassifier(TrainDataSVM);
        NN = new Clsi("NN", multilayerPerceptron, TrainDataSVM);
        System.out.println("NN done");
        System.out.println("F-Measure :" + NN.getEvaluation().fMeasure(1));
        /*System.out.println("Precision : "+DT.getEvaluation().precision(1));
        System.out.println("Recall: "+DT.getEvaluation().recall(1));
        System.out.println("Error rate: "+DT.getEvaluation().errorRate());
        System.out.println("% Correct: "+DT.getEvaluation().pctCorrect());
        System.out.println("% Incorrect:  "+DT.getEvaluation().pctIncorrect());*/
        System.out.println("----------------------\n");
        sendMSG("NNOK,"+TrainDataSVM.size()+","+
                NN.getEvaluation().fMeasure(1)+","+
                NN.getEvaluation().precision(1)+","+
                NN.getEvaluation().falsePositiveRate(1)+","+
                NN.getEvaluation().kappa()+",");
        //sendMSG("NN,TR"+TrainDataSVM.size()+",FM"+NN.getEvaluation().fMeasure(1)+",PR"+NN.getEvaluation().precision(1));

        ok2 = 1;

    }

    void CallSVM(Instances TrainDataSVM) throws Exception {


        SMO smo = new SMO();
        smo.buildClassifier(TrainDataSVM);
        SVM = new Clsi("SVM", smo, TrainDataSVM);
        System.out.println("SVM done");
        System.out.println("F-Measure :" + SVM.getEvaluation().fMeasure(1));
        /*System.out.println("Precision : "+DT.getEvaluation().precision(1));
        System.out.println("Recall: "+DT.getEvaluation().recall(1));
        System.out.println("Error rate: "+DT.getEvaluation().errorRate());
        System.out.println("% Correct: "+DT.getEvaluation().pctCorrect());
        System.out.println("% Incorrect:  "+DT.getEvaluation().pctIncorrect());*/
        System.out.println("----------------------\n");
        sendMSG("SVMOK,"+TrainDataSVM.size()+","+
                SVM.getEvaluation().fMeasure(1)+","+
                SVM.getEvaluation().precision(1)+","+
                SVM.getEvaluation().falsePositiveRate(1)+","+
                SVM.getEvaluation().kappa()+",");
        //sendMSG("SVM,TR"+TrainDataSVM.size()+",FM"+SVM.getEvaluation().fMeasure(1)+",PR"+SVM.getEvaluation().precision(1));


        ok3 = 1;


    }

    public static ArrayList<Attack> getAttcks() throws Exception {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("Test");

        DBCollection collection = database.getCollection("Attacks");
        DBCursor cursor = collection.find();
        ArrayList<DBObject> arrayList = (ArrayList<DBObject>) cursor.toArray();


        ArrayList<Attack> attacks = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            attacks.add(new Attack(String.valueOf(arrayList.get(i).get("id")), String.valueOf(arrayList.get(i).get("name")), String.valueOf(arrayList.get(i).get("category"))));
        }
        return attacks;
    }

    public static Instances getTrainDatasetDT(String CollectionName) throws Exception {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("Test");

        DBCollection collection = database.getCollection(CollectionName);
        DBCursor cursor = collection.find();
        ArrayList<DBObject> arrayList = (ArrayList<DBObject>) cursor.toArray();

        Instances instances = new DataSource("trainsmpl.arff").getDataSet();
        instances.setClassIndex(instances.numAttributes() - 1);
        instances.clear();

        for (int i = 0; i < arrayList.size(); i++) {
            PacketDT packetDT = new PacketDT(arrayList.get(i));
            instances.add(packetDT.toInstance(instances));
        }
        return instances;
    }

    public void Classiy() throws Exception {

        Instances TrainDataDT = getTrainDatasetDT("PacketsTrainDT");
        Instances TrainDataSVM = getTrainDatasetDT("PacketsTrainSVMNN");
        ok1=0;
        ok2=0;
        ok3=0;

        attacks = getAttcks();

        CallDT(TrainDataDT);
        CallSVM(TrainDataSVM);
        CallNN(TrainDataSVM);

        if (ok1 == 1 && ok2 == 1 && ok3 == 1) {

            sendMSG("LastTr:"+methode());

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setContent("ClassifReady");
            AID dest = null;
            dest = new AID("MAS_IDS.Agents.ManagerAgent", AID.ISLOCALNAME);
            msg.addReceiver(dest);
            send(msg);

            try {
                ManagerAgent.addMessage(new Message(msg.getSender().getLocalName(),"MAS_IDS.Agents.ManagerAgent",msg.getContent()));
                //MAS_IDS.PlatformPara.NotifyMessages(new MAS_IDS.PlatfromObjects.Message(msg.getSender().getLocalName(),"MAS_IDS.Agents.ManagerAgent",msg.getContent()),0);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /*MAS_IDS.PlatfromObjects.Message messageListe;
            messageListe = new MAS_IDS.PlatfromObjects.Message(msg.getSender().getLocalName(), "MAS_IDS.Agents.ManagerAgent", msg.getContent());
            MAS_IDS.Agents.ManagerAgent.addMessage(messageListe);*/

        }


    }


    public int UpdateC() throws Exception {

        Instances TrainDataDT = getTrainDatasetDT("PacketsTrainDT");
        Instances TrainDataSVM = getTrainDatasetDT("PacketsTrainSVMNN");
        ok1=0;
        ok2=0;
        ok3=0;

        attacks = getAttcks();

        CallDT(TrainDataDT);
        CallSVM(TrainDataSVM);
        CallNN(TrainDataSVM);

        if (ok1 == 1 && ok2 == 1 && ok3 == 1) {

            return 1;
        }


        return 0;
    }

    static  void sendMSG(String message) throws IOException {

        Socket socket = new Socket("192.168.43.206",7801);
        //172.16.15.53

        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it





        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.write(message);
        pw.flush();
        pw.close();

        System.out.println("SEND");
        socket.close();
    }

    public static String methode(){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(String.valueOf(localDateTime.getMinute()).length()==1){
            return localDateTime.getYear()+"_"+localDateTime.getMonthValue()+"_"+localDateTime.getDayOfMonth()+
                    "_"+localDateTime.getHour()+"_O"+localDateTime.getMinute()+"_"+localDateTime.getSecond();
        }

        return localDateTime.getYear()+"_"+localDateTime.getMonthValue()+"_"+localDateTime.getDayOfMonth()+
                "_"+localDateTime.getHour()+"_"+localDateTime.getMinute()+"_"+localDateTime.getSecond();
    }


}