import jade.core.*;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class SubManagerAgent extends Agent {
    @Override
    protected void setup() {
        String containerID = getMyID(getAID().getLocalName());

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {




                /*
                ACLMessage msg = new ACLMessage( ACLMessage.INFORM );
                msg.setContent("SMC"+containerID+"Ready");
                AID dest = null;
                dest = new AID("ManagerAgent",AID.ISLOCALNAME);
                msg.addReceiver(dest);
                send(msg);

                Message messageListe;
                messageListe = new Message(msg.getSender().getLocalName(),"ManagerAgent",msg.getContent());
                ManagerAgent.addMessage(messageListe);*/

                AgentController agentController = null;
                try {
                    agentController = getAgent().getContainerController().createNewAgent("SnifferAgent_Container"+containerID,"SnifferAgent",null);
                    ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setSnifferAID("SnifferAgent_Container"+containerID);
                    agentController.start();
                } catch (StaleProxyException e) {
                    e.printStackTrace();
                }
            }
        });

        /*
        addBehaviour(new TickerBehaviour(this,5000) {
            @Override
            protected void onTick() {
                ACLMessage message = new ACLMessage(ACLMessage.INFORM);

                msg.setContent("ADT_"+String.valueOf(state));
                AID dest = null;
                dest = new AID("AnalysorAgent_Container"+cid, AID.ISLOCALNAME);
                msg.addReceiver(dest);
                System.out.println("send");
                send(msg);

                Message messageListe;
                messageListe = new Message(msg.getSender().getLocalName(), "AnalysorAgent_Container"+cid, msg.getContent());
                ManagerAgent.addMessage(messageListe);
            }
        });*/

        /*

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                ACLMessage msg= receive();
                if (msg!=null){
                    //System.out.println( " - " + myAgent.getLocalName() + " <- " + msg.getContent());

                    if(msg.getContent().equals("code1")){
                        addBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {

                                AgentController agentController = null;


                                try {
                                    agentController =  getAgent().getContainerController().createNewAgent("AnalysorAgent"+ManagerAgent.analysornumber+"_"+
                                                    getAgent().getContainerController().getContainerName(),
                                            "AnalysorAgent",null);
                                    agentController.start();
                                    ManagerAgent.analysornumber++;
                                } catch (ControllerException e) {
                                    e.printStackTrace();
                                }



                            }
                        });
                    }

                    if(msg.getContent().equals("codeSM1")){

                        ACLMessage message1 = new ACLMessage( ACLMessage.INFORM );
                        message1.setContent("codeAM1");
                        AID dest;
                        dest = new AID("ManagerAgent",AID.ISLOCALNAME);

                        message1.addReceiver(dest);
                        send(message1);

                        Message messageListe = new Message();
                        messageListe = new Message(message1.getSender().getLocalName(),"ManagerAgent",message1.getContent());
                        //ManagerAgent.messages.add(messageListe);
                        ManagerAgent.addMessage(messageListe);


                    }

                    if(msg.getContent().equals("codeSM2")){

                        ACLMessage message1 = new ACLMessage( ACLMessage.INFORM );
                        message1.setContent("codeAM2");
                        AID dest;
                        dest = new AID("ManagerAgent",AID.ISLOCALNAME);

                        message1.addReceiver(dest);

                        send(message1);

                        Message messageListe = new Message();
                        messageListe = new Message(message1.getSender().getLocalName(),"ManagerAgent",message1.getContent());
                        //ManagerAgent.messages.add(messageListe);
                        ManagerAgent.addMessage(messageListe);

                    }
                }

            }
        });
*/

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if(message!=null){
                    if(message.getContent().equals("Check")){

                        ManagerAgent.containers.get(Integer.parseInt(containerID)-1).setInformed(true);

                        addBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                msg.setContent("Check");
                                AID dest = null;
                                dest = new AID("AnalysorAgent_Container"+String.valueOf(containerID), AID.ISLOCALNAME);
                                msg.addReceiver(dest);
                                send(msg);
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ManagerAgent.addMessage(new Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+String.valueOf(containerID),msg.getContent()));
                                    //PlatformPara.NotifyMessages(new Message(msg.getSender().getLocalName(),"AnalysorAgent_Container"+String.valueOf(containerID),msg.getContent()),0);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ACLMessage rcv = receive();
                                if(rcv!=null){
                                    if(rcv.getContent().equals("Check_OK")){

                                        if (rcv.getSender().getLocalName().equals("AnalysorAgent_Container"+containerID)){
                                            System.out.println("----------------------------------------\nAnalysorAgent_Container"+containerID+"\t is ALIVE\n" +
                                                    "-------------------------------------------------");
                                        }

                                    }
                                }else {
                                    //DELETE IT AGnet
                                }
                            }
                        });

                        addBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                                msg.setContent("Check");
                                AID dest = null;
                                dest = new AID("SnifferAgent_Container"+String.valueOf(containerID), AID.ISLOCALNAME);
                                msg.addReceiver(dest);
                                send(msg);
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    ManagerAgent.addMessage(new Message(msg.getSender().getLocalName(),"SnifferAgent_Container"+String.valueOf(containerID),msg.getContent()));
                                    //PlatformPara.NotifyMessages(new Message(msg.getSender().getLocalName(),"SnifferAgent_Container"+String.valueOf(containerID),msg.getContent()),0);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                ACLMessage rcv = receive();
                                if(rcv!=null){
                                    if(rcv.getContent().equals("Check_OK")){

                                        if (rcv.getSender().getLocalName().equals("SnifferAgent_Container"+containerID)){

                                            System.out.println("----------------------------------------\nSnifferAgent_Container"+containerID+"\t is ALIVE\n" +
                                                    "-------------------------------------------------");
                                        }

                                    }
                                }else {
                                    //DELETE IT AGnet
                                }
                            }
                        });

                    }
                }
            }
        });

    }

    public String getMyID(String AID){
        return AID.replace("SubManagerAgent_Container","");
    }
}
