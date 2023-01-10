import weka.core.Instance;

public class PacketSniffer {
    private Instance instance;

    private boolean solved;

    PacketSniffer(){

    }

    PacketSniffer(Instance instance,boolean solved){
        this.instance=instance;
        this.solved = solved;

    }


    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instanceSVMNN) {
        this.instance = instanceSVMNN;
    }





}
