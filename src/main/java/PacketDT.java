import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class PacketDT {

    private String duration;
    private String protocol_type;
    private String service;
    private String flag;
    private String src_bytes;
    private String dst_bytes;
    private String logged_in;
    private String count;
    private String srv_count;
    private String serror_rate;
    private String srv_serror_rate;
    private String rerror_rate;
    private String srv_rerror_rate;
    private String same_srv_rate;
    private String diff_srv_rate;
    private String srv_diff_host_rate;
    private String dst_host_count;
    private String dst_host_srv_count;
    private String dst_host_same_srv_rate;
    private String dst_host_diff_srv_rate;
    private String dst_host_same_src_port_rate;
    private String dst_host_srv_diff_host_rate;
    private String dst_host_serror_rate;
    private String dst_host_srv_serror_rate;
    private String dst_host_rerror_rate;
    private String dst_host_srv_rerror_rate;
    private String classy;

    PacketDT(){

    }

    PacketDT(String duration,String protocol_type,String service,String flag,String src_bytes,String dst_bytes,
             String logged_in,String count,String srv_count,String serror_rate,String srv_serror_rate,String rerror_rate,
             String srv_rerror_rate,String same_srv_rate,String diff_srv_rate,String srv_diff_host_rate,
             String dst_host_count,String dst_host_srv_count,String dst_host_same_srv_rate,String dst_host_diff_srv_rate,
             String dst_host_same_src_port_rate,String dst_host_srv_diff_host_rate,String dst_host_serror_rate,
             String dst_host_srv_serror_rate,String dst_host_rerror_rate,String dst_host_srv_rerror_rate,String classy){

        this.duration = duration;
        this.protocol_type = protocol_type;
        this.service = service;
        this.flag = flag;
        this.src_bytes = src_bytes;
        this.dst_bytes = dst_bytes;
        this.logged_in = logged_in;
        this.count = count;
        this.srv_count = srv_count;
        this.serror_rate = serror_rate;
        this.srv_serror_rate = srv_serror_rate;
        this.rerror_rate = rerror_rate;
        this.srv_rerror_rate = srv_rerror_rate;
        this.same_srv_rate = same_srv_rate;
        this.diff_srv_rate = diff_srv_rate;
        this.srv_diff_host_rate = srv_diff_host_rate;
        this.dst_host_count = dst_host_count;
        this.dst_host_srv_count = dst_host_srv_count;
        this.dst_host_same_srv_rate = dst_host_same_srv_rate;
        this.dst_host_diff_srv_rate = dst_host_diff_srv_rate;
        this.dst_host_same_src_port_rate = dst_host_same_src_port_rate;
        this.dst_host_srv_diff_host_rate = dst_host_srv_diff_host_rate;
        this.dst_host_serror_rate = dst_host_serror_rate;
        this.dst_host_srv_serror_rate = dst_host_srv_serror_rate;
        this.dst_host_rerror_rate = dst_host_rerror_rate;
        this.dst_host_srv_rerror_rate = dst_host_srv_rerror_rate;
        this.classy = classy;
    }

    PacketDT(DBObject object){



        this.duration =String.valueOf( object.get("duration"));
        this.protocol_type =String.valueOf( object.get("protocol_type"));
        this.service =String.valueOf( object.get("service"));
        this.flag =String.valueOf( object.get("flag"));
        this.src_bytes =String.valueOf( object.get("src_bytes"));
        this.dst_bytes =String.valueOf( object.get("dst_bytes"));
        this.logged_in =String.valueOf( object.get("logged_in"));
        this.count =String.valueOf( object.get("count"));
        this.srv_count =String.valueOf( object.get("srv_count"));
        this.serror_rate =String.valueOf( object.get("serror_rate"));
        this.srv_serror_rate =String.valueOf( object.get("srv_serror_rate"));
        this.rerror_rate =String.valueOf( object.get("rerror_rate"));
        this.srv_rerror_rate =String.valueOf( object.get("srv_rerror_rate"));
        this.same_srv_rate =String.valueOf( object.get("same_srv_rate"));
        this.diff_srv_rate =String.valueOf( object.get("diff_srv_rate"));
        this.srv_diff_host_rate =String.valueOf( object.get("srv_diff_host_rate"));
        this.dst_host_count =String.valueOf( object.get("dst_host_count"));
        this.dst_host_srv_count =String.valueOf( object.get("dst_host_srv_count"));
        this.dst_host_same_srv_rate =String.valueOf( object.get("dst_host_same_srv_rate"));
        this.dst_host_diff_srv_rate =String.valueOf( object.get("dst_host_diff_srv_rate"));
        this.dst_host_same_src_port_rate =String.valueOf( object.get("dst_host_same_src_port_rate"));
        this.dst_host_srv_diff_host_rate =String.valueOf( object.get("dst_host_srv_diff_host_rate"));
        this.dst_host_serror_rate =String.valueOf( object.get("dst_host_serror_rate"));
        this.dst_host_srv_serror_rate =String.valueOf( object.get("dst_host_srv_serror_rate"));
        this.dst_host_rerror_rate =String.valueOf( object.get("dst_host_rerror_rate"));
        this.dst_host_srv_rerror_rate =String.valueOf( object.get("dst_host_srv_rerror_rate"));
        this.classy =String.valueOf( object.get("class"));



    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getProtocol_type() {
        return protocol_type;
    }

    public void setProtocol_type(String protocol_type) {
        this.protocol_type = protocol_type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSrc_bytes() {
        return src_bytes;
    }

    public void setSrc_bytes(String src_bytes) {
        this.src_bytes = src_bytes;
    }

    public String getDst_bytes() {
        return dst_bytes;
    }

    public void setDst_bytes(String dst_bytes) {
        this.dst_bytes = dst_bytes;
    }

    public String getLogged_in() {
        return logged_in;
    }

    public void setLogged_in(String logged_in) {
        this.logged_in = logged_in;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSrv_count() {
        return srv_count;
    }

    public void setSrv_count(String srv_count) {
        this.srv_count = srv_count;
    }

    public String getSerror_rate() {
        return serror_rate;
    }

    public void setSerror_rate(String serror_rate) {
        this.serror_rate = serror_rate;
    }

    public String getSrv_serror_rate() {
        return srv_serror_rate;
    }

    public void setSrv_serror_rate(String srv_serror_rate) {
        this.srv_serror_rate = srv_serror_rate;
    }

    public String getRerror_rate() {
        return rerror_rate;
    }

    public void setRerror_rate(String rerror_rate) {
        this.rerror_rate = rerror_rate;
    }

    public String getSrv_rerror_rate() {
        return srv_rerror_rate;
    }

    public void setSrv_rerror_rate(String srv_rerror_rate) {
        this.srv_rerror_rate = srv_rerror_rate;
    }

    public String getSame_srv_rate() {
        return same_srv_rate;
    }

    public void setSame_srv_rate(String same_srv_rate) {
        this.same_srv_rate = same_srv_rate;
    }

    public String getDiff_srv_rate() {
        return diff_srv_rate;
    }

    public void setDiff_srv_rate(String diff_srv_rate) {
        this.diff_srv_rate = diff_srv_rate;
    }

    public String getSrv_diff_host_rate() {
        return srv_diff_host_rate;
    }

    public void setSrv_diff_host_rate(String srv_diff_host_rate) {
        this.srv_diff_host_rate = srv_diff_host_rate;
    }

    public String getDst_host_count() {
        return dst_host_count;
    }

    public void setDst_host_count(String dst_host_count) {
        this.dst_host_count = dst_host_count;
    }

    public String getDst_host_srv_count() {
        return dst_host_srv_count;
    }

    public void setDst_host_srv_count(String dst_host_srv_count) {
        this.dst_host_srv_count = dst_host_srv_count;
    }

    public String getDst_host_same_srv_rate() {
        return dst_host_same_srv_rate;
    }

    public void setDst_host_same_srv_rate(String dst_host_same_srv_rate) {
        this.dst_host_same_srv_rate = dst_host_same_srv_rate;
    }

    public String getDst_host_diff_srv_rate() {
        return dst_host_diff_srv_rate;
    }

    public void setDst_host_diff_srv_rate(String dst_host_diff_srv_rate) {
        this.dst_host_diff_srv_rate = dst_host_diff_srv_rate;
    }

    public String getDst_host_same_src_port_rate() {
        return dst_host_same_src_port_rate;
    }

    public void setDst_host_same_src_port_rate(String dst_host_same_src_port_rate) {
        this.dst_host_same_src_port_rate = dst_host_same_src_port_rate;
    }

    public String getDst_host_srv_diff_host_rate() {
        return dst_host_srv_diff_host_rate;
    }

    public void setDst_host_srv_diff_host_rate(String dst_host_srv_diff_host_rate) {
        this.dst_host_srv_diff_host_rate = dst_host_srv_diff_host_rate;
    }

    public String getDst_host_serror_rate() {
        return dst_host_serror_rate;
    }

    public void setDst_host_serror_rate(String dst_host_serror_rate) {
        this.dst_host_serror_rate = dst_host_serror_rate;
    }

    public String getDst_host_srv_serror_rate() {
        return dst_host_srv_serror_rate;
    }

    public void setDst_host_srv_serror_rate(String dst_host_srv_serror_rate) {
        this.dst_host_srv_serror_rate = dst_host_srv_serror_rate;
    }

    public String getDst_host_rerror_rate() {
        return dst_host_rerror_rate;
    }

    public void setDst_host_rerror_rate(String dst_host_rerror_rate) {
        this.dst_host_rerror_rate = dst_host_rerror_rate;
    }

    public String getDst_host_srv_rerror_rate() {
        return dst_host_srv_rerror_rate;
    }

    public void setDst_host_srv_rerror_rate(String dst_host_srv_rerror_rate) {
        this.dst_host_srv_rerror_rate = dst_host_srv_rerror_rate;
    }

    public String getClassy() {
        return classy;
    }

    public void setClassy(String classy) {
        this.classy = classy;
    }

    public Instance toInstance(Instances dataset){
        Instance instance=new DenseInstance(dataset.numAttributes());
        instance.setDataset(dataset);

        instance.setValue(0,Double.parseDouble(this.duration));
        instance.setValue(1,this.protocol_type);
        instance.setValue(2,this.service);
        instance.setValue(3,this.flag);
        instance.setValue(4,Double.parseDouble(this.src_bytes));
        instance.setValue(5,Double.parseDouble(this.dst_bytes));
        instance.setValue(6,this.logged_in);
        instance.setValue(7,Double.parseDouble(this.count));
        instance.setValue(8,Double.parseDouble(this.srv_count));
        instance.setValue(9,Double.parseDouble(this.serror_rate));
        instance.setValue(10,Double.parseDouble(this.srv_serror_rate));
        instance.setValue(11,Double.parseDouble(this.rerror_rate));
        instance.setValue(12,Double.parseDouble(this.srv_rerror_rate));
        instance.setValue(13,Double.parseDouble(this.same_srv_rate));
        instance.setValue(14,Double.parseDouble(this.diff_srv_rate));
        instance.setValue(15,Double.parseDouble(this.srv_diff_host_rate));
        instance.setValue(16,Double.parseDouble(this.dst_host_count));
        instance.setValue(17,Double.parseDouble(this.dst_host_srv_count));
        instance.setValue(18,Double.parseDouble(this.dst_host_same_srv_rate));
        instance.setValue(19,Double.parseDouble(this.dst_host_diff_srv_rate));
        instance.setValue(20,Double.parseDouble(this.dst_host_same_src_port_rate));
        instance.setValue(21,Double.parseDouble(this.dst_host_srv_diff_host_rate));
        instance.setValue(22,Double.parseDouble(this.dst_host_serror_rate));
        instance.setValue(23,Double.parseDouble(this.dst_host_srv_serror_rate));
        instance.setValue(24,Double.parseDouble(this.dst_host_rerror_rate));
        instance.setValue(25,Double.parseDouble(this.dst_host_srv_rerror_rate));
        instance.setValue(26,this.classy);



        return  instance;
    }

    public void toPacketDT(Instance instance){


        this.duration = String.valueOf(instance.value(0));
        this.protocol_type = String.valueOf(instance.value(1));
        this.service = String.valueOf(instance.value(2));
        this.flag = String.valueOf(instance.value(3));
        this.src_bytes = String.valueOf(instance.value(4));
        this.dst_bytes = String.valueOf(instance.value(5));
        this.logged_in = String.valueOf(instance.value(6));
        this.count = String.valueOf(instance.value(7));
        this.srv_count = String.valueOf(instance.value(8));
        this.serror_rate = String.valueOf(instance.value(9));
        this.srv_serror_rate = String.valueOf(instance.value(10));
        this.rerror_rate = String.valueOf(instance.value(11));
        this.srv_rerror_rate = String.valueOf(instance.value(12));
        this.same_srv_rate = String.valueOf(instance.value(13));
        this.diff_srv_rate = String.valueOf(instance.value(14));
        this.srv_diff_host_rate = String.valueOf(instance.value(15));
        this.dst_host_count = String.valueOf(instance.value(16));
        this.dst_host_srv_count = String.valueOf(instance.value(17));
        this.dst_host_same_srv_rate = String.valueOf(instance.value(18));
        this.dst_host_diff_srv_rate = String.valueOf(instance.value(19));
        this.dst_host_same_src_port_rate = String.valueOf(instance.value(20));
        this.dst_host_srv_diff_host_rate = String.valueOf(instance.value(21));
        this.dst_host_serror_rate = String.valueOf(instance.value(22));
        this.dst_host_srv_serror_rate = String.valueOf(instance.value(23));
        this.dst_host_rerror_rate = String.valueOf(instance.value(24));
        this.dst_host_srv_rerror_rate = String.valueOf(instance.value(25));
        this.classy = String.valueOf(instance.value(26));


    }


    public DBObject toDBObject(){
        DBObject dbObject = new BasicDBObject().append("duration",this.duration)
                .append("protocol_type",this.protocol_type)
                .append("service",this.service)
                .append("flag",this.flag)
                .append("src_bytes",this.src_bytes)
                .append("dst_bytes",this.dst_bytes)
                .append("logged_in",this.logged_in)
                .append("count",this.count)
                .append("srv_count",this.srv_count)
                .append("serror_rate",this.serror_rate)
                .append("srv_serror_rate",this.srv_serror_rate)
                .append("rerror_rate",this.rerror_rate)
                .append("srv_rerror_rate",this.srv_rerror_rate)
                .append("same_srv_rate",this.same_srv_rate)
                .append("diff_srv_rate",this.diff_srv_rate)
                .append("srv_diff_host_rate",this.srv_diff_host_rate)
                .append("dst_host_count",this.dst_host_count)
                .append("dst_host_srv_count",this.dst_host_srv_count)
                .append("dst_host_same_srv_rate",this.dst_host_same_srv_rate)
                .append("dst_host_diff_srv_rate",this.dst_host_diff_srv_rate)
                .append("dst_host_same_src_port_rate",this.dst_host_same_src_port_rate)
                .append("dst_host_srv_diff_host_rate",this.dst_host_srv_diff_host_rate)
                .append("dst_host_serror_rate",this.dst_host_serror_rate)
                .append("dst_host_srv_serror_rate",this.dst_host_srv_serror_rate)
                .append("dst_host_rerror_rate",this.dst_host_rerror_rate)
                .append("dst_host_srv_rerror_rate",this.dst_host_srv_rerror_rate)
                .append("class",this.classy)
        ;


        return dbObject;
    }







    /*

    public static void main(String[] args)throws Exception {
        DataSource dataSource = new DataSource("C:\\Users\\pc\\Desktop\\IDSData3\\DT\\KDDTrainDT.arff");
        Instances train = dataSource.getDataSet();
        train.setClassIndex(train.numAttributes()-1);


        String x = "\"";

        /*
        for(int i=0;i<train.numAttributes();i++){
            if(train.attribute(i).isNumeric()){
                System.out.println("instance.setValue("+i+",Double.parseDouble(this."+train.attribute(i).name()+"));");
            }else{
                System.out.println("instance.setValue("+i+",this."+train.attribute(i).name()+");");
            }

        }*/

        /*DBObject person = new BasicDBObject("_id", "jo")
                .append("name", "Jo Bloggs")
                .append("address", new BasicDBObject("street", "123 Fake St")
                        .append("city", "Faketon")
                        .append("state", "MA")
                        .append("zip", 12345))
                .append("books", books);
        for(int i=0;i<train.numAttributes();i++){
            System.out.println(".append(\""+train.attribute(i).name()+"\",this."+train.attribute(i).name()+")");
        }



    }*/
}
