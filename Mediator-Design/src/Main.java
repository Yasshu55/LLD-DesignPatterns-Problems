import java.util.ArrayList;
import java.util.List;

interface AirplaneSessionMediator{
    void broadcastChange(String change, Airplane airplane);
    void join(Airplane airplane);
}

class AirplaneSessionManager implements AirplaneSessionMediator{
    List<Airplane> airplaneList = new ArrayList<>();

    @Override
    public void broadcastChange(String change, Airplane sender) {
        for(Airplane airplane : airplaneList){
            if(airplane != sender ){
                airplane.receiveUpdate(change, sender);
            }
        }
    }

    @Override
    public void join(Airplane airplane) {
        airplaneList.add(airplane);
    }
}


class Airplane{
    protected String name;
    protected AirplaneSessionMediator mediator;

    public Airplane(String name, AirplaneSessionMediator mediator){
        this.name = name;
        this.mediator = mediator;
    }

    public void sendUpdate(String update){
        System.out.println(name + " Airplane is sending update : " + update);
        mediator.broadcastChange(update, this);
    }

    public void receiveUpdate(String change, Airplane sender){
        System.out.println(name + " got change from " + sender.name + ": \"" + change + "\"");
    }
}

public class Main {
    public static void main(String[] args) {
        AirplaneSessionMediator mediator = new AirplaneSessionManager();
        Airplane a1 = new Airplane("Boeing-1", mediator);
        Airplane a2 = new Airplane("Boeing-2", mediator);
        Airplane a3 = new Airplane("Boeing-3", mediator);

        mediator.join(a1);
        mediator.join(a2);
        mediator.join(a3);

        a1.sendUpdate("Emergency landing! Low fuel!");
    }
}