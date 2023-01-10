import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PlatformPara {

    PlatformPara(){

    }
    public static  Runtime runtime = Runtime.instance();
    public static  Profile profile = new ProfileImpl();

    public  static ContainerController containerController;

    public static ArrayList<Boolean> alive= new ArrayList<>();
    public static String startTime;
    public static String startTime2;


    public static void NotifyMessages(Message message,int index) throws Exception{
        String fileName="C:\\Users\\pc\\Desktop\\IDSData+type\\Messages"+startTime+".txt";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        //DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String str = "-----------Message ID: "+index+" -----------"+"\nSender-: "+message.getSender()+"\nReciever-:"+message.getReciever()+
                "\nContent-:"+message.getContent()+"\nTime and Date-:"+dtf.format(message.getTime())+"\n\n\n";


        try {

            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter fileWritter = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write("\n"+str);
            bw.close();

            System.out.println("Successfully wrote to the file.");


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }

    /*public static void Print(String output){
        System.out.println(output);
        String fileName="C:\\Users\\pc\\Desktop\\IDSData+type\\Log"+startTime+".txt";

        //DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");


        try {

            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter fileWritter = new FileWriter(fileName,true);
            BufferedWriter bw = new BufferedWriter(fileWritter);
            bw.write("\n"+output);
            bw.close();

            System.out.println("Successfully wrote to the file.");


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }*/

    public static String methode(){

        LocalDateTime localDateTime = LocalDateTime.now();
        if(String.valueOf(localDateTime.getMinute()).length()==1){
            startTime2=localDateTime.getHour()+":0"+localDateTime.getMinute()+":"+localDateTime.getSecond();
            return localDateTime.getYear()+"_"+localDateTime.getMonthValue()+"_"+localDateTime.getDayOfMonth()+
                    "_"+localDateTime.getHour()+"_"+localDateTime.getMinute()+"_"+localDateTime.getSecond();
        }

        startTime2=localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond();
        return localDateTime.getYear()+"_"+localDateTime.getMonthValue()+"_"+localDateTime.getDayOfMonth()+
                "_"+localDateTime.getHour()+"_"+localDateTime.getMinute()+"_"+localDateTime.getSecond();
    }

}
