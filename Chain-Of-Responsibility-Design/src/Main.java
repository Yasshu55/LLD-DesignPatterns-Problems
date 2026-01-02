abstract class SupportHandler{
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String reqType);
}

class GeneralSupportHandler extends SupportHandler{

    @Override
    public void handleRequest(String reqType) {
        if(reqType.equalsIgnoreCase("General")){
            System.out.println("General support handling");
        } else if(nextHandler != null){
            nextHandler.handleRequest(reqType);
        }
    }
}

class AISupportHandler extends SupportHandler{

    @Override
    public void handleRequest(String reqType) {
        if(reqType.equalsIgnoreCase("AI")){
            System.out.println("AI support handling");
        } else if(nextHandler != null){
            nextHandler.handleRequest(reqType);
        }
    }
}

class DeveloperSupportHandler extends SupportHandler{

    @Override
    public void handleRequest(String reqType) {
        if(reqType.equalsIgnoreCase("Developer")){
            System.out.println("Developer support handling");
        } else if(nextHandler != null){
            nextHandler.handleRequest(reqType);
        }
    }
}

class ManagerSupportHandler extends SupportHandler{

    @Override
    public void handleRequest(String reqType) {
        if(reqType.equalsIgnoreCase("Manager")){
            System.out.println("Manager support handling");
        } else if(nextHandler != null){
            nextHandler.handleRequest(reqType);
        } else{
            System.out.println("No support available!");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SupportHandler generalSupport = new GeneralSupportHandler();
        SupportHandler aiSupport =  new AISupportHandler();
        SupportHandler developerSupport = new DeveloperSupportHandler();
        SupportHandler managerSupport = new ManagerSupportHandler();

        generalSupport.setNextHandler(aiSupport);
        aiSupport.setNextHandler(developerSupport);
        developerSupport.setNextHandler(managerSupport);

        generalSupport.handleRequest("AI");
    }
}