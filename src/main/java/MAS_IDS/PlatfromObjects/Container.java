package MAS_IDS.PlatfromObjects;

import java.util.ArrayList;

public class Container {
    public static int count=1;
    private String ContainerID;
    private String SubManagerAID;
    private String SnifferAID;
    private String AnalysorAID;
    private ArrayList<PacketSniffer> packetsDetected;

    private ArrayList<PacketSniffer> all;
    private ArrayList<PacketDetected> packetClassified;
    private int cuurentstate;

    private String stateSubManager;
    private String stateSniffer;
    private String stateAnalysor;
    private boolean informed;
    private boolean agentInformer;



    public Container(){

        this.ContainerID=""+count;
        this.packetsDetected = new ArrayList<>();
        this.all = new ArrayList<>();
        this.packetClassified = new ArrayList<>();
        this.cuurentstate =0;
        this.stateSubManager="Actif";
        this.stateSniffer="Actif";
        this.stateAnalysor="Actif";
        this.informed=false;
        this.agentInformer=false;

        count++;
    }
    public Container(String subManagerAID,String snifferAID,String analysorAID){
        this.ContainerID=""+count;
        count++;
        this.SubManagerAID=subManagerAID;
        this.AnalysorAID = analysorAID;
        this.SnifferAID = snifferAID;
        this.packetsDetected = new ArrayList<>();
        this.all = new ArrayList<>();

        this.packetClassified = new ArrayList<>();
        this.cuurentstate=0;

        this.informed=false;
        this.agentInformer=false;

        this.stateSubManager="Actif";
        this.stateSniffer="Actif";
        this.stateAnalysor="Actif";


    }

    public String getContainerID() {
        return ContainerID;
    }

    public void setContainerID(String containerID) {
        ContainerID = containerID;
    }

    public String getSubManagerAID() {
        return SubManagerAID;
    }

    public void setSubManagerAID(String subManagerAID) {
        SubManagerAID = subManagerAID;
    }



    public String getSnifferAID() {
        return SnifferAID;
    }

    public void setSnifferAID(String snifferAID) {
        SnifferAID = snifferAID;
    }



    public ArrayList<PacketSniffer> getPacketsDetected() {
        return packetsDetected;
    }

    public void setPacketsDetected(ArrayList<PacketSniffer> packetsDetected) {
        this.packetsDetected = packetsDetected;
    }

    public String getAnalysorAID() {
        return AnalysorAID;
    }

    public void setAnalysorAID(String analysorAID) {
        AnalysorAID = analysorAID;
    }



    public ArrayList<PacketDetected> getPacketClassified() {
        return packetClassified;
    }

    public void setPacketClassified(ArrayList<PacketDetected> packetClassified) {
        this.packetClassified = packetClassified;
    }

    public int getCuurentstate() {
        return cuurentstate;
    }

    public void setCuurentstate(int cuurentstate) {
        this.cuurentstate = cuurentstate;
    }


    public String getStateSubManager() {
        return stateSubManager;
    }

    public void setStateSubManager(String stateSubManager) {
        this.stateSubManager = stateSubManager;
    }

    public String getStateSniffer() {
        return stateSniffer;
    }

    public void setStateSniffer(String stateSniffer) {
        this.stateSniffer = stateSniffer;
    }

    public String igetStateAnalysor() {
        return stateAnalysor;
    }

    public void setStateAnalysor(String stateAnalysor) {
        this.stateAnalysor = stateAnalysor;
    }

    public ArrayList<PacketSniffer> getAll() {
        return all;
    }

    public void setAll(ArrayList<PacketSniffer> all) {
        this.all = all;
    }


    public MessageContainer getThemAll(){
        int counterNormal=0;
        int counterAnomaly=0;
        int counterDos=0;
        int counterProbe=0;
        int counterR2L=0;
        int counterU2R=0;
        int counterDT=0;
        int counterSVM=0;
        int counterNN=0;

        for(int i=0;i<packetClassified.size();i++){
            String cat = packetClassified.get(i).getCategory();
            if(cat.equals("Normal")){
                counterNormal+=1;
            }else {
                counterAnomaly+=1;
                if(cat.equals("Dos")){
                    counterDos+=1;
                }
                if(cat.equals("Probe")){
                    counterProbe+=1;
                }

                if(cat.equals("R2L")){
                    counterR2L+=1;
                }


                if(cat.equals("U2R")){
                    counterU2R+=1;
                }
            }

            String byWho = packetClassified.get(i).getBywho();
            if(byWho.contains("DT")){
                counterDT+=1;
            }
            if(byWho.contains("SVM")){
                counterSVM+=1;
            }
            if(byWho.contains("NN")){
                counterNN+=1;
            }


        }

        return new MessageContainer(Integer.parseInt(ContainerID),packetClassified.size(),
                counterNormal,counterAnomaly,counterDos,counterProbe,counterR2L,counterU2R,
                counterDT,counterSVM,counterNN);

    }

    public boolean isInformed() {
        return informed;
    }

    public void setInformed(boolean informed) {
        this.informed = informed;
    }


    public boolean isAgentInformer() {
        return agentInformer;
    }

    public void setAgentInformer(boolean agentInformer) {
        this.agentInformer = agentInformer;
    }
}

