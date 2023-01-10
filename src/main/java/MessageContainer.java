import java.util.ArrayList;

public class MessageContainer {

    private int MachineNumber;
    private int TotalPackets;
    private int NormalNumber;
    private int AnomalyNumber;
    private int DosNumber;
    private int ProbeNumber;
    private int R2LNumber;
    private int U2RNumber;
    private int DTNumber;
    private int SVMNumber;
    private int NNNumber;

    MessageContainer(){
        this.MachineNumber=0;
        this.TotalPackets=0;
        this.NormalNumber=0;
        this.AnomalyNumber=0;
        this.DosNumber=0;
        this.ProbeNumber=0;
        this.R2LNumber=0;
        this.U2RNumber=0;
        this.DTNumber=0;
        this.SVMNumber=0;
        this.NNNumber=0;
    }

    MessageContainer(int machineNumber,int totalPackets,int normalNumber,int anomalyNumber,int dosNumber,int probeNumber,int r2LNumber,int u2RNumber,int dtNumber,int svmNumber,int nnNumber){
        this.MachineNumber=machineNumber;
        this.TotalPackets=totalPackets;
        this.NormalNumber=normalNumber;
        this.AnomalyNumber=anomalyNumber;
        this.DosNumber=dosNumber;
        this.ProbeNumber=probeNumber;
        this.R2LNumber=r2LNumber;
        this.U2RNumber=u2RNumber;
        this.DTNumber=dtNumber;
        this.SVMNumber=svmNumber;
        this.NNNumber=nnNumber;
    }

    MessageContainer(String recieved){
        ArrayList<String> arrayList = new ArrayList<>();
        String word="";
        for(int i=0;i<recieved.length();i++){
            if(recieved.charAt(i)==','){
                arrayList.add(word);
                word="";
            }else{
                word+=recieved.charAt(i);
            }
        }


        this.MachineNumber=Integer.parseInt(arrayList.get(0));
        this.TotalPackets=Integer.parseInt(arrayList.get(1));
        this.NormalNumber=Integer.parseInt(arrayList.get(2));
        this.AnomalyNumber=Integer.parseInt(arrayList.get(3));
        this.DosNumber=Integer.parseInt(arrayList.get(4));
        this.ProbeNumber=Integer.parseInt(arrayList.get(5));
        this.R2LNumber=Integer.parseInt(arrayList.get(6));
        this.U2RNumber=Integer.parseInt(arrayList.get(7));
        this.DTNumber=Integer.parseInt(arrayList.get(8));
        this.SVMNumber=Integer.parseInt(arrayList.get(9));
        this.NNNumber=Integer.parseInt(arrayList.get(10));


    }

    String toSend(){

        return this.MachineNumber+","+ this.TotalPackets+","+ this.NormalNumber+","+ this.AnomalyNumber+","+
                this.DosNumber+","+ this.ProbeNumber+","+ this.R2LNumber+","+ this.U2RNumber+","+
                this.DTNumber+","+ this.SVMNumber+","+ this.NNNumber+",";
    }

    public void Printit(){
        System.out.println(this.MachineNumber+"\n"+
        this.TotalPackets+"\n"+
        this.NormalNumber+"\n"+
        this.AnomalyNumber+"\n"+
        this.DosNumber+"\n"+
        this.ProbeNumber+"\n"+
        this.R2LNumber+"\n"+
        this.U2RNumber+"\n"+
        this.DTNumber+"\n"+
        this.SVMNumber+"\n"+
        this.NNNumber+"\n");
    }



    public int getMachineNumber() {
        return MachineNumber;
    }

    public void setMachineNumber(int machineNumber) {
        MachineNumber = machineNumber;
    }

    public int getTotalPackets() {
        return TotalPackets;
    }

    public void setTotalPackets(int totalPackets) {
        TotalPackets = totalPackets;
    }

    public int getNormalNumber() {
        return NormalNumber;
    }

    public void setNormalNumber(int normalNumber) {
        NormalNumber = normalNumber;
    }

    public int getAnomalyNumber() {
        return AnomalyNumber;
    }

    public void setAnomalyNumber(int anomalyNumber) {
        AnomalyNumber = anomalyNumber;
    }

    public int getDosNumber() {
        return DosNumber;
    }

    public void setDosNumber(int dosNumber) {
        DosNumber = dosNumber;
    }

    public int getProbeNumber() {
        return ProbeNumber;
    }

    public void setProbeNumber(int probeNumber) {
        ProbeNumber = probeNumber;
    }

    public int getR2LNumber() {
        return R2LNumber;
    }

    public void setR2LNumber(int r2LNumber) {
        R2LNumber = r2LNumber;
    }

    public int getU2RNumber() {
        return U2RNumber;
    }

    public void setU2RNumber(int u2RNumber) {
        U2RNumber = u2RNumber;
    }

    public int getDTNumber() {
        return DTNumber;
    }

    public void setDTNumber(int DTNumber) {
        this.DTNumber = DTNumber;
    }

    public int getSVMNumber() {
        return SVMNumber;
    }

    public void setSVMNumber(int SVMNumber) {
        this.SVMNumber = SVMNumber;
    }

    public int getNNNumber() {
        return NNNumber;
    }

    public void setNNNumber(int NNNumber) {
        this.NNNumber = NNNumber;
    }
}
