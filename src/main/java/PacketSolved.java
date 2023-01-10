import weka.core.Instance;

public class PacketSolved {


    private Instance instance;
    private boolean solved;

    PacketSolved(){

    }

    PacketSolved(Instance instance,boolean solved){

        this.instance = instance;
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

    public void setInstance(Instance instance) {
        this.instance = instance;
    }



}
