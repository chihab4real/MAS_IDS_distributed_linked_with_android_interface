import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class CreateContainers extends SimpleBehaviour {

    Boolean condition = false;
    int n=1;

    @Override
    public void action() {

        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(Profile.CONTAINER_NAME, "Container"+n);
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        Container container = new Container();
        container.setSubManagerAID("SubManagerAgent_Container"+container.getContainerID());
        ManagerAgent.containers.add(container);



        ContainerController containerController =  runtime.createAgentContainer(profile);
        AgentController agentController = null;
        try {
            agentController = containerController.createNewAgent("SubManagerAgent_Container"+n,"SubManagerAgent",null);
            agentController.start();
            ShutdownPlatform shutdownPlatform = new ShutdownPlatform();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }


        n++;

        if(n==3){
            ManagerAgent.classREady=1;
            condition=true;
        }


    }

    @Override
    public boolean done() {
        return condition;
    }
}
