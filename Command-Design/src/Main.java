import java.util.Stack;

interface ButtonCommand {
    void execute();
    void undo();
}

class Light{

    public void on() {
        System.out.println("Light is on!");
    }

    public void off() {
        System.out.println("Light is off!");
    }
}

class AC {

    public void on() {
        System.out.println("AC is on!");
    }

    public void off() {
        System.out.println("AC is off!");
    }
}

class LightOnCommand implements ButtonCommand {
    private final Light light;

    LightOnCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements ButtonCommand {
    private final Light light;

    LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class AcOnCommand implements ButtonCommand {
    private final AC ac;

    AcOnCommand(AC ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }

    @Override
    public void undo() {
        ac.off();
    }
}

class AcOffCommand implements ButtonCommand {
    private final AC ac;

    AcOffCommand(AC ac){
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }

    @Override
    public void undo() {
        ac.on();
    }
}

class RemoteControl {
    private ButtonCommand[] buttons = new ButtonCommand[4];
    private Stack<ButtonCommand> commandsHistory = new Stack<>();


    void setCommand(int slot, ButtonCommand command){
        if(slot < buttons.length){
            buttons[slot] = command;
        } else {
            System.out.println("No such button is found");
        }
    }

    void pressButton(int slot){
        if(slot < buttons.length){
            buttons[slot].execute();
            commandsHistory.add(buttons[slot]);
        } else {
            System.out.println("No such button is found");
        }
    }

    void pressUndo(){
        if(!commandsHistory.isEmpty()){
            commandsHistory.pop().undo();
        } else {
            System.out.println("No operations to undo!");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        AC ac = new AC();

        RemoteControl remoteControl =  new RemoteControl();
        remoteControl.setCommand(0, new LightOnCommand(light));
        remoteControl.setCommand(1, new LightOffCommand(light));
        remoteControl.setCommand(2, new AcOnCommand(ac));
        remoteControl.setCommand(3, new AcOffCommand(ac));

        remoteControl.pressButton(0);
        remoteControl.pressButton(5);
        remoteControl.pressUndo();
        remoteControl.pressUndo();
        remoteControl.pressUndo();



    }
}